package designpattern.proxy;

/**
 * 没有实现接口的目标类。
 * JDK 动态代理要求目标类实现接口，CGLIB 可以通过生成子类来代理这种普通类。
 */
public class NoInterfacePayService {
    public void pay(String userRole, String userId, int amount) {
        System.out.println("无接口真实支付执行，userId=" + userId + ", amount=" + amount);
    }
}
