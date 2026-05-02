package designpattern.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * 深拷贝：复制对象本身，也复制对象内部的引用类型字段。
 */
@Setter
@Getter
public class DeepCopyOrder implements Cloneable {
    private String orderNo;
    private String userId;
    private int amount;
    private Address address;

    public DeepCopyOrder(String orderNo, String userId, int amount, Address address) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.amount = amount;
        this.address = address;
    }

    @Override
    public DeepCopyOrder clone() {
        try {
            // 第一步：先使用 Object.clone() 复制订单对象本身。
            DeepCopyOrder copy = (DeepCopyOrder) super.clone();
            // 第二步：手动复制引用字段 Address，避免新旧订单共享同一个地址对象。
            copy.address = this.address.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            // DeepCopyOrder 已经实现 Cloneable，正常情况下不会进入这里。
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "DeepCopyOrder{" +
            "orderNo='" + orderNo + '\'' +
            ", userId='" + userId + '\'' +
            ", amount=" + amount +
            ", address=" + address +
            '}';
    }
}
