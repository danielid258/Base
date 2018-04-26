package com.base.thread;

/**
 * @desc: Created by Fengyun on 4/24/2018.
 * 
 * ThreadLocal并不是一个Thread, 而是Thread的局部变量, 也许把它命名为ThreadLocalVariable更容易让人理解一些
 * 
 * 使用ThreadLocal维护变量时, ThreadLocal为每个使用该变量的线程提供独立的变量副本, 所以每一个线程都可以独立地改变自己的副本, 而不会影响其它线程所对应的副本
 * 
 * 从线程的角度看, 目标变量就象是线程的本地变量, 这也是类名中“Local”所要表达的意思
 */
public class ThreadLocalDemo {
}
