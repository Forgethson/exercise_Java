package designpattern.proxy;

public class CglibProxyDemo {
    public static void main(String[] args) {
        // NoInterfacePayService 没有实现接口，适合用 CGLIB 生成子类代理。
        NoInterfacePayService payService = CglibProxyFactory.createProxy(NoInterfacePayService.class);

        payService.pay("USER", "USER_001", 100);
        payService.pay("USER", "USER_001", 2000);
        payService.pay("ADMIN", "ADMIN_001", 2000);
    }
}
