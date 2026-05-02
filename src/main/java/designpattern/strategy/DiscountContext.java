package designpattern.strategy;

/**
 * 策略上下文：持有一个具体策略，并把计算逻辑委托给策略对象。
 */
public class DiscountContext {
    private final DiscountStrategy discountStrategy;

    public DiscountContext(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public int calculate(int originalAmount) {
        return discountStrategy.calculate(originalAmount);
    }
}
