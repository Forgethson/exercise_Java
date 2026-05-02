package designpattern.facade;

/**
 * 外观类：为复杂下单流程提供一个统一、简单的入口。
 * 调用方只需要调用 placeOrder，不需要知道内部涉及哪些子系统。
 */
public class OrderFacade {
    private final RiskService riskService = new RiskService();
    private final StockService stockService = new StockService();
    private final CouponService couponService = new CouponService();
    private final PaymentService paymentService = new PaymentService();
    private final OrderService orderService = new OrderService();

    public void placeOrder(String userId, String skuId, int amount) {
        riskService.check(userId, amount);
        stockService.lock(skuId);
        couponService.use(userId);
        paymentService.pay(userId, amount);
        orderService.create(userId, skuId, amount);
    }
}
