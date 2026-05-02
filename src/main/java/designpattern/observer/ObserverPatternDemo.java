package designpattern.observer;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        OrderPaySubject orderPaySubject = new OrderPaySubject();

        // 注册观察者：支付成功后，这些观察者都会收到通知。
        orderPaySubject.addObserver(new SmsNotifyObserver());
        orderPaySubject.addObserver(new PointRewardObserver());
        orderPaySubject.addObserver(new CouponSendObserver());
        orderPaySubject.addObserver(new PayLogObserver());

        OrderPayEvent event = new OrderPayEvent("ORDER_001", "USER_001", 168);
        orderPaySubject.paySuccess(event);
    }
}
