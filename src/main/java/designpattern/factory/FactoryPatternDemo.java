package designpattern.factory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        PayService aliPay = PayFactory.create("ali");
        aliPay.pay(100);

        PayService wechatPay = PayFactory.create("wechat");
        wechatPay.pay(200);
    }
}
