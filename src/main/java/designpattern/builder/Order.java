package designpattern.builder;

/**
 * 建造者模式：将复杂对象的构建过程和对象本身分离，让调用方可以按步骤、可读地创建对象。
 * 工厂模式关注创建哪一种对象，建造者模式关注一个复杂对象如何被创建出来。
 */
public class Order {
    private final String orderNo;
    private final String userId;
    private final int amount;
    private final String payType;
    private final String address;
    private final boolean useCoupon;
    private final String couponName;
    private final String remark;

    private Order(Builder builder) {
        this.orderNo = builder.orderNo;
        this.userId = builder.userId;
        this.amount = builder.amount;
        this.payType = builder.payType;
        this.address = builder.address;
        this.useCoupon = builder.useCoupon;
        this.couponName = builder.couponName;
        this.remark = builder.remark;
    }

    public static class Builder {
        private String orderNo;
        private String userId;
        private int amount;
        private String payType;
        private String address;
        private boolean useCoupon;
        private String couponName;
        private String remark;

        public Builder orderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder payType(String payType) {
            this.payType = payType;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder useCoupon(boolean useCoupon) {
            this.useCoupon = useCoupon;
            return this;
        }

        public Builder couponName(String couponName) {
            this.couponName = couponName;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", payType='" + payType + '\'' +
                ", address='" + address + '\'' +
                ", useCoupon=" + useCoupon +
                ", couponName='" + couponName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
