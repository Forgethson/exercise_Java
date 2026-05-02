package designpattern.facade;

public class OrderService {
    public void create(String userId, String skuId, int amount) {
        System.out.println("订单记录创建成功，userId=" + userId + ", skuId=" + skuId + ", amount=" + amount);
    }
}
