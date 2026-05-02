package designpattern.strategy;

public class FullReduceStrategy implements DiscountStrategy {
    private final int threshold;
    private final int reduceAmount;

    public FullReduceStrategy(int threshold, int reduceAmount) {
        this.threshold = threshold;
        this.reduceAmount = reduceAmount;
    }

    @Override
    public int calculate(int originalAmount) {
        if (originalAmount >= threshold) {
            return originalAmount - reduceAmount;
        }
        return originalAmount;
    }
}
