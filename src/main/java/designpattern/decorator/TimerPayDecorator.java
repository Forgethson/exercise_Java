package designpattern.decorator;

public class TimerPayDecorator extends PayServiceDecorator {
    public TimerPayDecorator(PayService payService) {
        super(payService);
    }

    @Override
    public void pay(String userId, int amount) {
        long start = System.currentTimeMillis();
        try {
            // 先调用下一级对象，finally 中统一统计整个调用链的耗时。
            payService.pay(userId, amount);
        } finally {
            long cost = System.currentTimeMillis() - start;
            System.out.println("支付耗时：" + cost + " ms");
        }
    }
}
