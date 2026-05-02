package designpattern.singleton;

/**
 * 饿汉式单例：类加载时直接创建实例。
 * 优点是实现简单、线程安全；缺点是不管是否使用，实例都会提前创建。
 */
public class SingletonEager {
    // 类加载时立即创建实例；static final 保证引用只会被赋值一次。
    private static final SingletonEager INSTANCE = new SingletonEager();

    // 私有构造方法，禁止外部通过 new 创建新实例。
    private SingletonEager() {
    }

    // 对外暴露全局访问点，所有调用方拿到的都是同一个实例。
    public static SingletonEager getInstance() {
        return INSTANCE;
    }

    public void doSomething() {
        System.out.println("饿汉式单例执行业务方法");
    }
}
