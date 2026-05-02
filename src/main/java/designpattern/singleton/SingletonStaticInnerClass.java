package designpattern.singleton;

/**
 * 静态内部类单例：利用 JVM 的类加载机制实现懒加载和线程安全。
 * 外部类加载时不会创建实例，第一次调用 getInstance() 时才加载内部类。
 */
public class SingletonStaticInnerClass {
    // 私有构造方法，禁止外部直接创建实例。
    private SingletonStaticInnerClass() {
    }

    // 静态内部类只有在被主动使用时才会加载，因此实现了懒加载。
    private static class SingletonHolder {
        // JVM 类加载过程保证静态字段初始化只执行一次，因此天然线程安全。
        private static final SingletonStaticInnerClass INSTANCE = new SingletonStaticInnerClass();
    }

    // 第一次调用该方法时触发 SingletonHolder 加载，从而创建单例实例。
    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void doSomething() {
        System.out.println("静态内部类单例执行业务方法");
    }
}
