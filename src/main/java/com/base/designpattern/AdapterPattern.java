package com.base.designpattern;

/**
 * @desc: Created by Fengyun on 4/25/2018.
 *
 *适配器模式
 *
 * 将一个类的接口转换成客户端希望的另外一个接口 适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 *
 * 角色
 *      目标(Target) 期待得到的接口 也就是符合调用者需求的接口
 *
 *      源(Adapee)  不符合要求的接口 也就是需要做适配适配的接口
 *
 *      适配器(Adaper) 适配器模式的核心 适配器把源接口转换成目标接口 显然,这一角色必须是具体类
 *
 * 优点
 *      复用性
 *          需要使用现有的类 而现有类的接口不符合需求　适配器模式可以让已经实现的功能被复用
 *
 *      扩展性
 *          在实现适配器时　可以自由调用已经实现的功能　从而自然地扩展系统的功能
 *
 * 缺点
 *      过多使用适配器会让系统非常零乱 不直观
 *      比如　表面看到调用的是接口A的方法,其实内部被适配成了B接口的实现
 *
 *      所以适配器模式不适合在详细设计阶段使用它 可以把它作为一种补偿模式 用来处理系统后期扩展和修改
 *
 * 适用场景
 *      已经存在的类的接口不符合我们的需求
 *
 *      创建一个可以复用的类，使得该类可以与其他不相关的类或不可预见的类协同工作
 *
 *      使用一些已经存在的子类而不需要对其进行子类化来匹配接口
 *
 *      旧的系统开发的类已经实现了一些功能，但是客户端却只能以另外接口的形式访问，但我们不希望手动更改原有类的时候
 *
 */

public class AdapterPattern {
    //1 类适配器模式
    // Adaper extends Adaptee implements Target

    public class Adaptee {
        public void already() {
            System.out.println("这是已经存在的类中 需要使用到的方法");
        }
    }

    interface Target {
        public void request();
    }

    /**
     * 想要实现这个Target接口 同时用上已有的Adaptee类 但是不想重构
     *
     * 此时可以定义一个适配器类 继承已有的类 然后实现期待的接口
     */
    class Adapter extends Adaptee implements Target{
        public void request() {
            super.already();
        }
    }


    /**
     * 测试
     */
    public void test(){
        Adapter adapter = new Adapter();
        adapter.request();
    }




    //2 对象适配器模式
    //适配器类关联已有的Adaptee类 然后实现目标接口 这种方式不需要继承源类
    class Adapter2 implements Target{

        //不用继承关系连接到Adaptee 使用委派关系连接到Adaptee
        private Adaptee adaptee;

        //通过构造函数把 Adaptee 构造到适配器类中
        public Adapter2 (Adaptee adaptee) {
            this.adaptee= adaptee;
        }

        @Override
        public void request() {
            this.adaptee.already();
        }
    }

    /**
     * 测试
     */
    public void test2(){
        Target adapter = new Adapter2(new Adaptee());
        adapter.request();
    }
}

