package com.base.java8.lambda;

import com.base.model.User;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

/**
 * @desc: Created by Fengyun on 4/16/2018.
 *
 * 方法引用
 *
 * Lambda表达式中的方法体 已经有其他方法实现了 此时可以实用方法引用
 *
 * 引用方式
 *
 *  对象::实例方法
 *
 *  类::静态方法
 *
 *  类::实例方法
 *
 *
 *  构造器引用: 类名::new
 *
 *
 *
 */
public class MethodReferenceDemo {
    //数组引用
    public void test6() {
        //Function<Integer, String[]> function = (size) -> new String[size];
        Function<Integer, String[]> function = String[]::new;

        String[] arrary = function.apply(10);
        System.out.println(arrary.length);
    }


    //类名::new   调用的构造函数是 参数列表和函数式接口中参数列表相同的构造器
    public void test5() {
        //Supplier<User> supplier = () -> new User();
        Supplier<User> supplier = User::new;

        //BiFunction<Integer, String, User> function = (id, name) -> new User(id, name);
        BiFunction<Integer, String, User> function = User::new;

        User tom = function.apply(1, "tom");
        System.out.println(tom);
    }
    //private User getUser()


    //类::实例方法
    public void test4() {
        List<String> list = new ArrayList<>();

        //remooveReapetor(list, (x, y) -> x.equals(y));

        // ***** 调用实例方法[equals]
        // 但是Lambda表达式参数列表[(x, y)]中的第一个参数[x]是方法的调用对象
        // 其他参数[y]是调用方法的参数
        remooveReapetor(list, String::equals);
    }
    private void remooveReapetor(List<String> list,BiPredicate<String,String> predicate) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (predicate.test(list.get(i), list.get(j))) {
                    list.remove(i);
                    i--;
                }
            }
        }
    }

    //类::静态方法
    public void test3() {
        List<User> userList = new ArrayList<>();
        //User max = Collections.max(userList, (x, y) -> x.getAge().compareTo(y.getAge()));
        //User max = Collections.max(userList, (x, y) -> Integer.compare(x.getAge(), y.getAge()));
        User max = Collections.max(userList, (x, y) -> Integer.compare(x.getAge(), y.getAge()));

        List<Integer> integerList = new ArrayList<>();
        Collections.sort(integerList, Integer::compare);

        max.getAge();

    }

    //对象::实例方法
    public void test2() {
        User user = new User();
        //Supplier<String> supplier = () -> user.getName();
        Supplier<String> supplier = user::getName;

        Supplier<Integer> sup = user::getAge;

        String s = supplier.get();
        System.out.println(s);
    }

    //对象::实例方法
    public void test1() {
        PrintStream out = System.out;

        //Consumer<String> consumer = (x) -> out.println(x);
        Consumer<String> consumer = out::println;
        consumer.accept("10");

    }
}
