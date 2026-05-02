package designpattern.decorator;

public class LogPayDecorator extends PayServiceDecorator {
    public LogPayDecorator(PayService payService) {
        super(payService);
    }

    @Override
    public void pay(String userId, int amount) {
        // 调用下一级对象之前增加日志能力。
        System.out.println("支付日志：准备支付，userId=" + userId + ", amount=" + amount);
        payService.pay(userId, amount);
        // 调用下一级对象之后继续增加日志能力。
        System.out.println("支付日志：支付结束");
    }
}
