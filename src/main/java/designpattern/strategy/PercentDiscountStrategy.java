package designpattern.strategy;

public class PercentDiscountStrategy implements DiscountStrategy {
    private final int percent;

    public PercentDiscountStrategy(int percent) {
        this.percent = percent;
    }

    @Override
    public int calculate(int originalAmount) {
        return originalAmount * percent / 100;
    }
}
