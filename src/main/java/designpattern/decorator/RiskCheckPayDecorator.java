package designpattern.decorator;

public class RiskCheckPayDecorator extends PayServiceDecorator {
    public RiskCheckPayDecorator(PayService payService) {
        super(payService);
    }

    @Override
    public void pay(String userId, int amount) {
        // 风控逻辑放在真实支付之前，校验不通过就不再调用下一级对象。
        if (amount > 10000) {
            throw new IllegalArgumentException("支付金额过大，触发风控，amount=" + amount);
        }
        System.out.println("风控校验通过");
        payService.pay(userId, amount);
    }
}
