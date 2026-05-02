package designpattern.abstractfactory;

/**
 * 抽象工厂：用于创建一组相关或相互依赖的对象，而不需要指定它们的具体类。
 * 一个抽象工厂接口，定义一组产品的创建方法。
 * 一个具体工厂实现类，负责创建同一产品族下的一组具体产品。
 * 这里的产品族是同一支付渠道下的支付能力和退款能力。
 */
public interface PaymentFactory {
    PayService createPayService();

    RefundService createRefundService();
}
