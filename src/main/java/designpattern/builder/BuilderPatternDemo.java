package designpattern.builder;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        Order order = new Order.Builder()
                .orderNo("ORDER_001")
                .userId("USER_001")
                .amount(100)
                .payType("ali")
                .address("北京市朝阳区")
                .useCoupon(true)
                .couponName("优惠券A")
                .remark("尽快发货")
                .build();

        System.out.println(order);
    }
}
