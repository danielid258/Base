package com.base.designpattern;

import java.util.Vector;

/**
 * @desc: Created by Fengyun on 4/27/2018.
 *
 * 观察者模式
 *
 * 定义对象间一对多的依赖关系 使得当每一个对象改变状态 则所有依赖于它的对象都会得到通知并自动更新
 *
 * 角色
 *  被观察者抽象类
 *      可以是接口 也可以是抽象类或者具体的类 因为很多情况下会与其他的模式混用 所以使用抽象类的情况较多
 *
 *  被观察者具体实现类
 *      使用这个角色是为了便于扩展 可以在此角色中定义具体的业务逻辑
 *
 *  观察者抽象接口
 *      一般是一个接口 只有一个update方法 在被观察者状态发生变化时 这个方法就会被触发调用
 *
 *  观察者具体实现类
 *      将定义被观察者对象状态发生变化时所要处理的逻辑
 *
 * 优点
 *  观察者与被观察者之间是属于轻度关联 并且是抽象耦合的 对于两者来说都比较容易进行扩展
 *  
 *  观察者模式是一种常用的触发机制 它形成一条触发链 依次对各个观察者的方法进行处理
 *  同时 这也是观察者模式一个缺点 由于是链式触发 当观察者比较多的时候 性能会受到影响
 *
 */
public class ObserverPattern {
    //被观察者抽象类
    public abstract class Subject {
        //Vector容器用来存放观察者对象 是被观察者类的核心
        // 之所以使用Vector而不用List 是因为多线程中 Vector是安全的 而List则是不安全的

        private Vector<Observer> obs = new Vector<Observer>();

        //attach 向Vector中添加观察者
        public void addObserver(Observer obs){
            this.obs.add(obs);
        }
        //detachVector中移除观察者
        public void delObserver(Observer obs){
            this.obs.remove(obs);
        }
        // notify依次调用观察者的对应方法
        protected void notifyObserver(){
            for(Observer o: obs){
                o.update();
            }
        }
        public abstract void doSomething();
    }

    //被观察者具体实现类
    public class ConcreteSubject extends Subject {
        @Override
        public void doSomething() {
            System.out.println("被观察者事件反生");
            this.notifyObserver();
        }
    }

    //观察者抽象接口
    public interface Observer {
        public void update();
    }
    //观察者具体实现类1
    public class ConcreteObserver1 implements Observer {
        public void update() {
            System.out.println("观察者1收到信息 并进行处理。");
        }
    }
    //观察者具体实现类2
    public class ConcreteObserver2 implements Observer {
        public void update() {
            System.out.println("观察者2收到信息 并进行处理。");
        }
    }

    public void test() {
        //创建被观察者
        Subject sub = new ConcreteSubject();

        //添加观察者1
        sub.addObserver(new ConcreteObserver1());

        //添加观察者2
        sub.addObserver(new ConcreteObserver2());

        //通知观察者
        sub.doSomething();
    }
}
