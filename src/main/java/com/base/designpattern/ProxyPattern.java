package com.base.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @desc: Created by Fengyun on 4/26/2018.
 *
 * 代理模式
 *
 *      对象A不适合或者不能直接被其他对象引用 为A提供一个代理B 以控制对A的访问 代理对象B可以在调用者和目标对象A之间起到代理人的作用
 *
 * 角色
 *  公共接口 真实对象和代理对象的共同接口
 *
 *  目标类 代理角色所代表的真实对象 即最终要引用的对象
 *
 *  代理类 内部含有对真实对象的引用 可以对真实对象进行再次封装
 *
 * 分类
 *  静态代理
 *      代理类的字节码文件在程序运行前就已经存在 代理类和目标类的关系在运行前就确定了
 *
 *  动态代理
 *      代理类的源码在程序在运行期间由JVM根据反射等机制动态生成的 所以不存在代理类的字节码文件 代理和目标的关系在程序运行时确定
 *
 * 优点
 *      目标类只需要关注业务逻辑本身 保证了业务的重用性 这是代理的共有优点
 *
 *      协调调用者和被调用者 一定程度上降低了系统的耦合度
 */
public class ProxyPattern {

    public interface UserService{
        void query();

        void update();
    }
    //目标类
    public class UserServiceImpl implements UserService {
        @Override
        public void query() {
            System.out.println("do query");
        }

        @Override
        public void update() {
            System.out.println("do update");

        }
    }
    //<<========= 静态代理 ===========>>//
    //代理类
    public class UserServiceProxy implements UserService {

        private UserService userService;

        public UserServiceProxy(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void query() {
            System.out.println("log begin");
            userService.query();
            System.out.println("log commit");
        }

        @Override
        public void update() {
            System.out.println("transaciton begin");
            userService.update();
            System.out.println("transaction commit");
        }
    }

    //test
    public void test1() {
        UserServiceProxy proxy = new UserServiceProxy(new UserServiceImpl());
        proxy.query();
        proxy.update();
    }


    //<<========= 动态代理 ===========>>//

    //代理类处理器
    // implements java.lang.reflect.InvocationHandler
    public class UserServiceHandler implements InvocationHandler {
        private UserService userService;

        public UserServiceHandler(UserService userService) {
            this.userService = userService;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object object = null;
            //调用目标方法前做一些事

            object = method.invoke(userService, args);

            //调用目标方法后做一些事
            return object;
        }
    }


    //test
    public void test2() {
        //create handler instance
        UserServiceHandler userServiceHandler = new UserServiceHandler(new UserServiceImpl());

        //create proxy instance
        UserService proxy = (UserService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{UserService.class}, userServiceHandler);

        proxy.query();
        proxy.update();

    }

}
