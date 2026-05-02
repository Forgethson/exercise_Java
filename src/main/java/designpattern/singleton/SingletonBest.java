package designpattern.singleton;

// 静态内部类单例
public class SingletonBest {
    // 1. 私有构造方法
    private SingletonBest() {}

    // 2. 静态内部类：只有在调用getInstance时才会加载，延迟创建实例
    private static class SingletonHolder {
        private static final SingletonBest INSTANCE = new SingletonBest();
    }

    // 3. 公共访问方法
    public static SingletonBest getInstance() {
        return SingletonHolder.INSTANCE;
    }
}