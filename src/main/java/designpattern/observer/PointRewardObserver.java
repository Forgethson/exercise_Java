package designpattern.observer;

public class PointRewardObserver implements PaySuccessObserver {
    @Override
    public void onPaySuccess(OrderPayEvent event) {
        int points = event.getAmount() / 10;
        System.out.println("增加用户积分，userId=" + event.getUserId() + ", points=" + points);
    }
}
