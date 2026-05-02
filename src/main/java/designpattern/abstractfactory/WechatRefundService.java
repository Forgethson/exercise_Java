package designpattern.abstractfactory;

public class WechatRefundService implements RefundService {
    @Override
    public void refund(int amount) {
        System.out.println("微信退款：" + amount + " 元");
    }
}
