package designpattern.proxy;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        PayService payService = new PayServiceProxy(new RealPayService());

        payService.pay("USER", "USER_001", 100);
        payService.pay("USER", "USER_001", 2000);
        payService.pay("ADMIN", "ADMIN_001", 2000);
    }
}
