package designpattern.observer;

public class CouponSendObserver implements PaySuccessObserver {
    @Override
    public void onPaySuccess(OrderPayEvent event) {
        if (event.getAmount() >= 100) {
            System.out.println("发放优惠券，userId=" + event.getUserId() + ", coupon=满100减20");
            return;
        }
        System.out.println("支付金额不足 100，不发放优惠券");
    }
}
