package designpattern.abstractfactory;

public class WechatPaymentFactory implements PaymentFactory {
    @Override
    public PayService createPayService() {
        return new WechatPayService();
    }

    @Override
    public RefundService createRefundService() {
        return new WechatRefundService();
    }
}
