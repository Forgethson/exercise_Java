package designpattern.facade;

public class FacadePatternDemo {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();

        // 调用方只关心“下单”这个统一入口，不直接和多个子系统打交道。
        orderFacade.placeOrder("USER_001", "SKU_001", 100);
    }
}
