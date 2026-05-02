package designpattern.factorymethod;

public class AliPayFactory implements PayFactory {
    @Override
    public PayService create() {
        return new AliPayService();
    }
}
