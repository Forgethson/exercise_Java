package designpattern.simplefactory;

/**
 * 简单工厂：根据传入参数，决定创建并返回哪个具体产品对象。
 * 价值是把对象创建逻辑从业务代码中抽出来，集中管理。
 * 缺点是新增产品时，通常要修改工厂类。
 */
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
