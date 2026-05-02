package designpattern.factorymethod;

public class WechatPayFactory implements PayFactory {
    @Override
    public PayService create() {
        return new WechatPayService();
    }
}
