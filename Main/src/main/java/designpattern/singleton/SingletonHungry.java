package designpattern.singleton;

// 饿汉式单例
public class SingletonHungry {
    // 1. 私有静态实例（类加载时直接创建，保证唯一）
    private static final SingletonHungry INSTANCE = new SingletonHungry();

    // 2. 私有构造方法，禁止外部new
    private SingletonHungry() {
    }

    // 3. 公共静态方法，返回唯一实例
    public static SingletonHungry getInstance() {
        return INSTANCE;
    }
}