package designpattern.simplefactory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        // 调用方只传入类型参数，不直接 new 具体支付类。
        PayService aliPay = PayFactory.create("ali");
        aliPay.pay(100);

        PayService wechatPay = PayFactory.create("wechat");
        wechatPay.pay(200);
    }
}
