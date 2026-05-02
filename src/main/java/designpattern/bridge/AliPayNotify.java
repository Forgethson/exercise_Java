package designpattern.bridge;

public class AliPayNotify extends PayNotify {
    public AliPayNotify(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void notifyPayResult(String userId, int amount) {
        messageSender.send("支付宝支付成功，userId=" + userId + ", amount=" + amount);
    }
}
