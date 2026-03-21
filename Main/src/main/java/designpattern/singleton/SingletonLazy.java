package designpattern.singleton;

// 线程安全的懒汉式单例
public class SingletonLazy {
    // 1. 私有静态实例（初始为null，延迟创建）
    private static volatile SingletonLazy INSTANCE; // volatile防止指令重排序

    // 2. 私有构造方法
    private SingletonLazy() {
    }

    // 3. 双重检查锁（DCL），保证线程安全且性能优
    public static SingletonLazy getInstance() {
        if (INSTANCE == null) { // 第一次检查：避免每次调用都加锁
            synchronized (SingletonLazy.class) { // 加锁，保证同一时间只有一个线程进入
                if (INSTANCE == null) { // 第二次检查：防止多个线程等待锁后重复创建
                    INSTANCE = new SingletonLazy();
                }
            }
        }
        return INSTANCE;
    }
}
