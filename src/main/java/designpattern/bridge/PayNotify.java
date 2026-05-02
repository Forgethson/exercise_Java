package designpattern.bridge;

/**
 * 抽象部分：支付渠道通知。
 * 通过组合 MessageSender，把支付渠道和通知方式这两个变化维度桥接起来。
 */
public abstract class PayNotify {
    protected final MessageSender messageSender;

    protected PayNotify(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public abstract void notifyPayResult(String userId, int amount);
}
