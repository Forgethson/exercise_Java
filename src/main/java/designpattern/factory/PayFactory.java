package designpattern.factory;

public class PayFactory {
    private PayFactory() {
    }

    public static PayService create(String type) {
        if ("ali".equalsIgnoreCase(type)) {
            return new AliPayService();
        }
        if ("wechat".equalsIgnoreCase(type)) {
            return new WechatPayService();
        }
        throw new IllegalArgumentException("不支持的支付类型：" + type);
    }
}
