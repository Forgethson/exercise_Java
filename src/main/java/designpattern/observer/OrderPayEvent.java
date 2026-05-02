package designpattern.observer;

/**
 * 支付成功事件：把观察者需要的数据集中放在事件对象里。
 */
public class OrderPayEvent {
    private final String orderId;
    private final String userId;
    private final int amount;

    public OrderPayEvent(String orderId, String userId, int amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public int getAmount() {
        return amount;
    }
}
