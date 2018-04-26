package com.base.java8.stream;

import com.base.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * @desc: Created by Fengyun on 4/17/2018.
 *
 * 对原始数据源进行操作后  原始的数据源步发生改变  但是会返回一个新的数据源
 *
 * 操作步骤
 *      创建stream
 *      中间操作
 *      终止(终端)操作
 *
 */
public class Demo1 {
    public void test1() {
        //create stream
        //method 1: created by extends java.util.Collection<E>
        AtomicReference<List<String>> list = new AtomicReference<>(new ArrayList<>());
        Stream<String> stream = list.get().stream();

        //method 2:created by java.util.Arrays
        User[] users = new User[10];
        Stream<User> userStream = Arrays.stream(users);

        //method 3:created by Stream.of
        Stream<AtomicReference<List<String>>> atomicReferenceStream = Stream.of(list);

        //method 4:created by Stream.iterate
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        //iterator
        iterate.limit(100).forEach(System.out::println);

        //method 5:created by Stream.generate
        Stream<Double> generate = Stream.generate(() -> Math.random() * 100);
        //iterator
        generate.limit(10).forEach(System.out::println);

    }
    public static void main(String[] args) {

    }
}
