package com.base.designpattern;

/**
 * @desc: Created by Fengyun on 4/25/2018.
 * <p>
 * 工厂模式为创建对象提供过渡接口 以便将创建对象的具体过程隐藏卡里 达到提高灵活性的目的
 * <p>
 * 简单工厂模式 静态工厂模式
 * <p>
 * 工厂方法模式
 * <p>
 * 抽象工厂模式
 * <p>
 * <p>
 * 适用场景:
 * 在任何需要生成复杂对象的场景都可使用工厂方法模式; 但对于简单对象, 特别是只需要通过new就能创建的对象无需使用工厂模式
 * <p>
 * 工厂模式是一种典型的解耦模式 迪米特法则在其中表现的尤为明显 假如调用者自己组装产品需要增加依赖关系时 可以考虑使用工厂模式降低对象之间的耦合度
 * <p>
 * 工厂模式是依靠抽象架构,它把实例化产品的任务交由实现类完成,扩展性比较好; 因此 当需要系统有好的扩展性时可以考虑工厂模式
 */
public class FactoryPattern {
    class Engine {
        public void getStyle() {
            System.out.println("这是汽车的发动机");
        }
    }

    class Underpan {
        public void getStyle() {
            System.out.println("这是汽车的底盘");
        }
    }

    class Wheel {
        public void getStyle() {
            System.out.println("这是汽车的轮胎");
        }
    }

    class Music {
        public void getStyle() {
            System.out.println("这是汽车的Music");
        }
    }

    /**
     * 调用者
     */
    public void call() {
        Engine engine = new Engine();
        Underpan underpan = new Underpan();
        Wheel wheel = new Wheel();
        ICar car = new Car(engine, underpan, wheel);

        /**
         *
         可以看到
         调用者为了实例化汽车 还需要另外实例化发动机 底盘和轮胎, 而这些汽车的组件是与调用者无关的
         耦合度太高 且不利于扩展 严重违反了迪米特法则
         另外 本例中这些无关的发动机/底盘/轮胎 是具体的简单对象,通过new就可以创建, 但是实际应用中,可能这些组件也是复杂对象 调用者根本不知道怎样创建它们

         但如果使用工厂方法 整个架构就更加清晰
         */
    }

    //============ 以工厂方法模式重构以上代码 ==============//

    /**
     * 定义产品接口
     */
    interface ICar {
        void run();
    }

    /**
     * 定义产品实现类
     */
    class Car implements ICar {
        private Engine engine;
        private Underpan underpan;
        private Wheel wheel;

        public Car(Engine engine, Underpan underpan, Wheel wheel) {
            this.engine = engine;
            this.underpan = underpan;
            this.wheel = wheel;
        }

        @Override
        public void run() {
            System.out.println("runing ......");
        }
    }
    /**
     * 定义产品的另一个实现类
     */
    class CarPlus extends Car{
        private Music music;

        public CarPlus(Engine engine, Underpan underpan, Wheel wheel, Music music) {
            super(engine, underpan, wheel);
            this.music = music;
        }

        @Override
        public void run() {
            System.out.println("runing and music ......");
        }
    }

    /**
     * 定义工厂接口
     */
    interface IFactory {
        ICar createCar();
    }

    /**
     * 定义工厂实现类
     */
    class Factory implements IFactory {
        /**
         * 把对象的创建细节隐藏在工厂类的实现类中
         */
        @Override
        public ICar createCar() {
            Engine engine = new Engine();
            Underpan underpan = new Underpan();
            Wheel wheel = new Wheel();
            ICar car = new Car(engine, underpan, wheel);

            return car;
        }
    }

    /**
     * 定义工厂的另一个实现类 创建升级版的Icar对象
     */
    class FactoryPlus extends Factory{
        /**
         * 把对象的创建细节隐藏在工厂类的实现类中
         */
        @Override
        public ICar createCar() {
            Engine engine = new Engine();
            Underpan underpan = new Underpan();
            Wheel wheel = new Wheel();
            Music music = new Music();

            ICar car = new CarPlus(engine, underpan, wheel, music);
            return car;
        }
    }


    /**
     * 调用者
     */
    public class Client {
        public void test() {
            IFactory factory = null;
            //创建工厂
            factory = new Factory();
            //调通工厂创建需要的对象 不需要关心对象具体的创建细节
            ICar car = factory.createCar();
            //使用对象
            car.run();

            //创建其他类型的汽车 只需要再增加一个工厂类的实现即可
            factory = new FactoryPlus();
            ICar carPlus = factory.createCar();
            carPlus.run();
        }
        /**
         * 使用工厂方法后 大大降低调用端的耦合度
         * 对于工厂来说具备良好的扩展性 如果想组装其他类型的汽车 只需要再增加一个工厂类的实现即可
         */
    }
}
