package designpattern.factory;

public class WechatPayService implements PayService {
    @Override
    public void pay(int amount) {
        System.out.println("微信支付：" + amount + " 元");
    }
}
