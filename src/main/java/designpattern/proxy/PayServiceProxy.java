package designpattern.proxy;

/**
 * 静态代理：代理对象和真实对象实现同一个接口。
 * 代理对象负责访问控制，真实对象负责核心业务。
 */
public class PayServiceProxy implements PayService {
    private final PayService target;

    public PayServiceProxy(PayService target) {
        this.target = target;
    }

    @Override
    public void pay(String userRole, String userId, int amount) {
        System.out.println("代理校验：userRole=" + userRole + ", userId=" + userId + ", amount=" + amount);

        if (!"ADMIN".equals(userRole) && amount > 1000) {
            System.out.println("代理拒绝：普通用户不能发起超过 1000 元的大额支付");
            return;
        }

        target.pay(userRole, userId, amount);
        System.out.println("代理审计：支付调用结束");
    }
}
