package designpattern.singleton;

/**
 * 枚举单例：线程安全，并且天然防反射和反序列化破坏单例。
 * 实际生产中，如果没有继承限制，枚举单例通常是最稳妥的实现方式。
 */
public enum SingletonEnum {
    // 枚举常量本身就是全局唯一实例。
    INSTANCE;

    // 枚举单例也可以定义普通实例方法，用来承载业务行为。
    public void doSomething() {
        System.out.println("枚举单例执行业务方法");
    }
}
