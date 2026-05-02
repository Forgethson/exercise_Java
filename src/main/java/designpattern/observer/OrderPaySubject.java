package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者：负责维护观察者列表，并在支付成功后统一通知。
 */
public class OrderPaySubject {
    private final List<PaySuccessObserver> observers = new ArrayList<>();

    public void addObserver(PaySuccessObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PaySuccessObserver observer) {
        observers.remove(observer);
    }

    public void paySuccess(OrderPayEvent event) {
        System.out.println("订单支付成功，orderId=" + event.getOrderId() + ", amount=" + event.getAmount());
        notifyObservers(event);
    }

    private void notifyObservers(OrderPayEvent event) {
        for (PaySuccessObserver observer : observers) {
            observer.onPaySuccess(event);
        }
    }
}
