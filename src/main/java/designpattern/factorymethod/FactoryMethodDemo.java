package designpattern.factorymethod;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        // 调用方选择具体工厂，再由具体工厂创建对应的产品对象。
        PayFactory aliPayFactory = new AliPayFactory();
        PayService aliPay = aliPayFactory.create();
        aliPay.pay(100);

        PayFactory wechatPayFactory = new WechatPayFactory();
        PayService wechatPay = wechatPayFactory.create();
        wechatPay.pay(200);
    }
}
