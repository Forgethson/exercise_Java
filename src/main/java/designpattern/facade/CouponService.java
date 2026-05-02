package designpattern.facade;

public class CouponService {
    public void use(String userId) {
        System.out.println("优惠券核销成功，userId=" + userId);
    }
}
