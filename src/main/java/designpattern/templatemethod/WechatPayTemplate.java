package designpattern.templatemethod;

public class WechatPayTemplate extends AbstractPayTemplate {
    @Override
    protected void doPay(String userId, int amount) {
        System.out.println("微信扣款，userId=" + userId + ", amount=" + amount);
    }
}
