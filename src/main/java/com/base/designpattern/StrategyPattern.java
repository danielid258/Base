package com.base.designpattern;

/**
 * @desc: Created by Fengyun on 4/27/2018.
 *
 * 策略模式
 *
 * 定义一组算法 将每个算法都封装起来 并且使他们之间可以互换
 *
 * 角色
 *  抽象策略
 *      通常是一个接口 当各个实现类中存在着重复的逻辑时 则使用抽象类来封装这部分公共的代码
 *
 *  具体策略
 *      常由一组封装了算法的类来担任 这些类之间可以根据需要自由替换
 *
 *  封装类(上下文)
 *      对策略进行二次封装 避免高层模块对策略的直接调用
 *
 * 优点
 *      策略类之间可以自由切换
 *      易于扩展 基本可以在不改变原有代码的基础上增加一个新的策略
 *      避免使用多重条件
 * 缺点
 *      维护各个策略类会给开发带来额外开销
 *      必须对调用者暴露所有的策略类 因为使用哪种策略是由客户端来决定的
 *
 */
public class StrategyPattern {
    //抽象策略接口 当各个具体的策略类中存在着重复逻辑时 则使用抽象类来封装公共代码
    interface IStrategy{
        void doSomething();
    }

    //具体的策略1
    private class ConcreteStrategy1 implements IStrategy{

        @Override
        public void doSomething() {
            System.out.println("具体的策略1");
        }
    }

    //具体的策略2
    private class ConcreteStrategy2 implements IStrategy{

        @Override
        public void doSomething() {
            System.out.println("具体的策略2");
        }
    }

    //上下文 对策略进行二次封装
    private class Context{
        private IStrategy strategy;

        public Context(IStrategy strategy) {
            this.strategy = strategy;
        }

        public void execute() {
            strategy.doSomething();
        }
    }

    public void test() {
        //调用者不直接调用具体的策略 而是调用策略上下文
        IStrategy strategy1 = new ConcreteStrategy1();
        Context context = new Context(strategy1);
        context.execute();

        IStrategy strategy2 = new ConcreteStrategy2();
        context = new Context(strategy2);
        context.execute();
    }
}
