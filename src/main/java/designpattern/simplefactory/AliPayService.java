package designpattern.simplefactory;

public class AliPayService implements PayService {
    @Override
    public void pay(int amount) {
        System.out.println("支付宝支付：" + amount + " 元");
    }
}
