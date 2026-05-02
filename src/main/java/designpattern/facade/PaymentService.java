package designpattern.facade;

public class PaymentService {
    public void pay(String userId, int amount) {
        System.out.println("支付扣款成功，userId=" + userId + ", amount=" + amount);
    }
}
