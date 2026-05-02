package designpattern.bridge;

public class WechatPayNotify extends PayNotify {
    public WechatPayNotify(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void notifyPayResult(String userId, int amount) {
        messageSender.send("微信支付成功，userId=" + userId + ", amount=" + amount);
    }
}
