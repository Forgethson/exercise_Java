package designpattern.observer;

public class PayLogObserver implements PaySuccessObserver {
    @Override
    public void onPaySuccess(OrderPayEvent event) {
        System.out.println("记录支付日志，orderId=" + event.getOrderId() + ", userId=" + event.getUserId());
    }
}
