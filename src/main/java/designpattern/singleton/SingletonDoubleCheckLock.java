package designpattern.singleton;

/**
 * 双重检查锁单例：懒加载，并通过 volatile + synchronized 保证线程安全。
 * volatile 用于防止对象创建过程中的指令重排序。
 */
public class SingletonDoubleCheckLock {
    // volatile 保证 instance 对所有线程可见，并禁止 new 对象过程中的指令重排序。
    private static volatile SingletonDoubleCheckLock instance;

    // 私有构造方法，禁止外部直接创建实例。
    private SingletonDoubleCheckLock() {
    }

    public static SingletonDoubleCheckLock getInstance() {
        // 第一次检查：实例已经创建后，后续调用可以直接返回，避免每次都进入 synchronized。
        if (instance == null) {
            // 只有第一次创建实例时才需要加锁。
            synchronized (SingletonDoubleCheckLock.class) {
                // 第二次检查：防止多个线程等待锁后重复创建实例。
                if (instance == null) {
                    instance = new SingletonDoubleCheckLock();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("双重检查锁单例执行业务方法");
    }
}
