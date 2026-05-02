package designpattern.decorator;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        // 装饰器通过构造方法层层包裹，形成 Timer -> Log -> RiskCheck -> Basic 的调用链。
        PayService payService = new TimerPayDecorator(
                new LogPayDecorator(
                        new RiskCheckPayDecorator(
                                new BasicPayService()
                        )
                )
        );

        // 调用方仍然只面向 PayService，但实际执行时已经叠加了风控、日志、耗时统计能力。
        payService.pay("USER_001", 100);
    }
}
