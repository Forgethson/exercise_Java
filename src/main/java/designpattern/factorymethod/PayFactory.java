package designpattern.factorymethod;

/**
 * 工厂方法：定义一个创建对象的工厂接口，让具体工厂决定创建哪一种产品对象。
 * 相比简单工厂，新增产品时通常新增一个具体工厂类，而不是修改同一个工厂方法。
 */
public interface PayFactory {
    PayService create();
}
