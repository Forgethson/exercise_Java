package designpattern.templatemethod;

public class AliPayTemplate extends AbstractPayTemplate {
    @Override
    protected void doPay(String userId, int amount) {
        System.out.println("支付宝扣款，userId=" + userId + ", amount=" + amount);
    }
}
