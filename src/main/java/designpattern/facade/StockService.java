package designpattern.facade;

public class StockService {
    public void lock(String skuId) {
        System.out.println("库存锁定成功，skuId=" + skuId);
    }
}
