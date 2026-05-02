package designpattern.templatemethod;

/**
 * 模板方法：父类定义流程骨架，子类实现某些变化步骤。
 */
public abstract class AbstractPayTemplate {
    public final void pay(String userId, int amount) {
        checkOrder(userId, amount);
        doPay(userId, amount);
        sendNotify(userId, amount);
    }

    private void checkOrder(String userId, int amount) {
        System.out.println("校验订单，userId=" + userId + ", amount=" + amount);
    }

    protected abstract void doPay(String userId, int amount);

    private void sendNotify(String userId, int amount) {
        System.out.println("发送支付成功通知，userId=" + userId + ", amount=" + amount);
    }
}
