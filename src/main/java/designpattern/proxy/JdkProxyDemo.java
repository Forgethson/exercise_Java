package designpattern.proxy;

public class JdkProxyDemo {
    public static void main(String[] args) {
        // 这里没有手写 PayService 的代理类，而是由 JDK 在运行时生成代理对象。
        PayService payService = JdkProxyFactory.createProxy(new RealPayService());

        payService.pay("USER", "USER_001", 100);
        payService.pay("USER", "USER_001", 2000);
        payService.pay("ADMIN", "ADMIN_001", 2000);
    }
}
