package com.base.designpattern;

/**
 * @desc: Created by Fengyun on 4/24/2018.
 *
 * 单例 就是同一个类只有一个实例 并且整个系统都能访问该实例
 *
 * 单例模式共分为两大类
 *      饿汉式: 类装载时创建实例
 *      懒汉式: 第一次使用时创建实例
 */
public class Singleton {
    private Singleton() {
    }

    /**
     * 饿汉式
     *
     private static Singleton instance = new Singleton();

     public static Singleton getInstance() {
        return instance;
     }

    */

    /**
     * 懒汉式
     *
     * Double-Check + synchronized + volatile 实现线程安全
     */
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                /**
                 * singleton = new Singleton()  不是一个原子操作 在JVM中大概做了下面3件事
                 *
                 * 1 为 singleton变量 分配内存
                 *
                 * 2 调用 Singleton 的构造函数 初始化成员变量 形成实例
                 *
                 * 3 将singleton变量 指向 2中创建的实例的内存空间(执行完这步 singleton才是 非null 的)
                 *
                 * 在JVM的即时编译器中存在指令重排序的优化 也就是说上面的2和3的顺序是不能保证的 最终的执行顺序可能是 1-2-3 也可能是 1-3-2
                 * 如果是1-3-2 则线程一在3执行完毕2未执行时 线程二抢占了执行权 这时instance已经是非null了(但却没有初始化)
                 * 于是线程二会直接返回instance然后使用 于是顺理成章地报错
                 *
                 * 进一步解释: 由于instance正处于一个中间状态[不为null但是仍没有完成初始化]
                 * 此时 如果有其他线程刚好运行到第一层if (instance ==null)处 读取到的instance已经不为null
                 * 就直接把这个中间状态的instance拿去用了 于是产生问题 原因键在于线程T1对instance的写操作没有完成 线程T2就执行了读操作
                 *
                 *
                 * 解决方案为: 给instance的声明加上volatile关键字
                 *
                 * volatile 的一个作用是禁止指令重排
                 * 把instance声明为volatile后 对它的写操作就会有一个内存屏障 这样 在它的初始化完成前 不用会调用读操作
                 *
                 * 注意: volatile阻止的不是 singleton = new Singleton()内部[1-2-3]指令顺序的重排
                 * 而是保证在一个完整的写操作[1-2-3]完成之前 不会调用读操作 [if (instance == null)]
                 *
                 */
                instance = new Singleton();
            }
        }
        return instance;
    }

    /**
     * 静态内部类
     *
     *
        private static class SingletonHolder {
            private static final Singleton INSTANCE = new Singleton();
        }
        public static final Singleton getInstance() {
            return SingletonHolder.INSTANCE;
        }

     这种写法的巧妙之处在于:对于内部类SingletonHolder,它是一个饿汉式的单例实现,在SingletonHolder初始化的时候会由ClassLoader来保证同步,使INSTANCE是一个真单例

     同时,由于SingletonHolder是一个内部类,只在外部类的getInstance()中被使用,所以它被加载的时机也是在getInstance()方法第一次被调用的时候

     它利用了ClassLoader来保证了同步,同时又能让开发者控制类加载的时机

     从内部看是一个饿汉式的单例,从外部看来,又的确是懒汉式的实现
    */
}
