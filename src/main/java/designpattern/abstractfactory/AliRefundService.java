package designpattern.abstractfactory;

public class AliRefundService implements RefundService {
    @Override
    public void refund(int amount) {
        System.out.println("支付宝退款：" + amount + " 元");
    }
}
