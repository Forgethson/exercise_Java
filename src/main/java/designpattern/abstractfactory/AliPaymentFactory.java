package designpattern.abstractfactory;

public class AliPaymentFactory implements PaymentFactory {
    @Override
    public PayService createPayService() {
        return new AliPayService();
    }

    @Override
    public RefundService createRefundService() {
        return new AliRefundService();
    }
}
