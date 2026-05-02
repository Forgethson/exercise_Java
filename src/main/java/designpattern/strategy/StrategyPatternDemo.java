package designpattern.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        int originalAmount = 100;

        DiscountContext noDiscount = new DiscountContext(new NoDiscountStrategy());
        DiscountContext percentDiscount = new DiscountContext(new PercentDiscountStrategy(80));
        DiscountContext fullReduce = new DiscountContext(new FullReduceStrategy(100, 30));

        System.out.println("无优惠：" + noDiscount.calculate(originalAmount));
        System.out.println("八折优惠：" + percentDiscount.calculate(originalAmount));
        System.out.println("满 100 减 30：" + fullReduce.calculate(originalAmount));
    }
}
