package designpattern.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * 浅拷贝：只复制对象本身，引用类型字段仍然指向同一个对象。
 */
@Setter
@Getter
public class ShallowCopyOrder implements Cloneable {
    private String orderNo;
    private String userId;
    private int amount;
    private Address address;

    public ShallowCopyOrder(String orderNo, String userId, int amount, Address address) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.amount = amount;
        this.address = address;
    }

    @Override
    public ShallowCopyOrder clone() {
        try {
            // Object.clone() 默认是浅拷贝：只复制订单对象本身，不复制内部的 Address 对象。
            return (ShallowCopyOrder) super.clone();
        } catch (CloneNotSupportedException e) {
            // ShallowCopyOrder 已经实现 Cloneable，正常情况下不会进入这里。
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "ShallowCopyOrder{" +
            "orderNo='" + orderNo + '\'' +
            ", userId='" + userId + '\'' +
            ", amount=" + amount +
            ", address=" + address +
            '}';
    }
}
