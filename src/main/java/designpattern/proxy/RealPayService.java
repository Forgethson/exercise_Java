package designpattern.proxy;

public class RealPayService implements PayService {
    @Override
    public void pay(String userRole, String userId, int amount) {
        System.out.println("真实支付执行，userId=" + userId + ", amount=" + amount);
    }
}
