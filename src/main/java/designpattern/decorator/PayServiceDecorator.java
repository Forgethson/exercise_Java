package designpattern.decorator;

/**
 * 抽象装饰器：和被装饰对象实现同一个接口，并持有一个被装饰对象。
 */
public abstract class PayServiceDecorator implements PayService {
    // 装饰器内部持有同一个接口类型的对象，这是层层组合的基础。
    protected final PayService payService;

    protected PayServiceDecorator(PayService payService) {
        this.payService = payService;
    }
}
