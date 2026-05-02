package designpattern.decorator;

public class BasicPayService implements PayService {
    @Override
    public void pay(String userId, int amount) {
        System.out.println("执行基础支付，userId=" + userId + ", amount=" + amount);
    }
}
