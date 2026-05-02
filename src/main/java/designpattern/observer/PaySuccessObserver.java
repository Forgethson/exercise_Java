package designpattern.observer;

/**
 * 观察者接口：所有支付成功后的后续动作都实现这个接口。
 */
public interface PaySuccessObserver {
    void onPaySuccess(OrderPayEvent event);
}
