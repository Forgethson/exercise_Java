package designpattern.observer;

public class SmsNotifyObserver implements PaySuccessObserver {
    @Override
    public void onPaySuccess(OrderPayEvent event) {
        System.out.println("发送短信通知，userId=" + event.getUserId() + ", orderId=" + event.getOrderId());
    }
}
