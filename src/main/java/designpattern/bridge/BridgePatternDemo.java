package designpattern.bridge;

public class BridgePatternDemo {
    public static void main(String[] args) {
        PayNotify aliSmsNotify = new AliPayNotify(new SmsMessageSender());
        PayNotify aliEmailNotify = new AliPayNotify(new EmailMessageSender());
        PayNotify wechatSmsNotify = new WechatPayNotify(new SmsMessageSender());
        PayNotify wechatEmailNotify = new WechatPayNotify(new EmailMessageSender());

        // 支付渠道和通知方式可以自由组合，不需要为每一种组合都创建一个类。
        aliSmsNotify.notifyPayResult("USER_001", 100);
        aliEmailNotify.notifyPayResult("USER_001", 100);
        wechatSmsNotify.notifyPayResult("USER_002", 200);
        wechatEmailNotify.notifyPayResult("USER_002", 200);
    }
}
