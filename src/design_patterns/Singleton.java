package design_patterns;

/**
 * 使用静态内部类实现单例模式的创建(推荐)
 *
 * @Author: Liam
 * @Date: 2019/3/21 16:25
 */
public class Singleton {
    /**
     * 1.可以实现延迟加载的功能，只有在调用getInstance()的方法才会创建单例对象，并且是通过类加载器机制保证只创建一个单例对象；
     * 2.JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。
     *   在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
     * 3.对于Java类加载机制来说，当第一次访问类的静态字段的时候，会触发类加载，并且同一个类只加载一次。
     *   静态内部类也是如此，只会被加载一次，类加载过程由类加载器负责加锁，从而保证线程安全。
     */
    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
