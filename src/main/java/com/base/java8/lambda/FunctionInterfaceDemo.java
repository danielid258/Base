package com.base.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @desc: Created by Fengyun on 4/16/2018.
 *
 * Consumer<T>
 *     void accept(T t)
 *
 * Supplier<T>
 *     T get()
 *
 * Function<T,R>
 *     R apply(T t)
 *
 * Predicate<T>
 *     boolean test(T t)
 *
 *
 * java8内置函数式接口
 */
public class FunctionInterfaceDemo {
    public void test4() {
        List<String> list = Arrays.asList("hello", "test", "hi");
        stringFilter(list, (s) -> s.length() >= 2);

        for (String s :
                list) {
            System.out.println(s);
        }
    }
    List<String> stringFilter(List<String> original , Predicate<String> predicate) {
        List<String> list = new ArrayList<>();
        for (String s :
                original) {
            if (predicate.test(s))
                list.add(s);
        }
        return list;

    }


    public void test3() {
        convertToInteger("120", (x) -> new Integer(x));
    }
    private Integer convertToInteger(String original , Function<String,Integer> fun) {
        return fun.apply(original);
    }


    public void test2() {
        List<Integer> list = getList(10, () -> (int) (Math.random() * 100));
        for (Integer i :
                list) {
            System.out.println(i);
        }
    }
    private List<Integer> getList(int size, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Integer num = supplier.get();
            list.add(num);
        }
        return list;
    }



    public void test1() {
        consume(100d, (m) -> System.out.println("consumer money: "+m));
    }

    private void consume(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

}
