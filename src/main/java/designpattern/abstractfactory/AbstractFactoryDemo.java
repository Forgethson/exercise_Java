package designpattern.abstractfactory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 调用方选择一个具体工厂，就能获得同一产品族下的一组对象。
        // 支付宝产品族：支付宝支付 + 支付宝退款。
        PaymentFactory aliFactory = new AliPaymentFactory();
        aliFactory.createPayService().pay(100);
        aliFactory.createRefundService().refund(20);

        // 微信产品族：微信支付 + 微信退款。
        PaymentFactory wechatFactory = new WechatPaymentFactory();
        wechatFactory.createPayService().pay(200);
        wechatFactory.createRefundService().refund(50);
    }
}
