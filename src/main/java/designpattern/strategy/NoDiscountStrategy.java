package designpattern.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public int calculate(int originalAmount) {
        return originalAmount;
    }
}
