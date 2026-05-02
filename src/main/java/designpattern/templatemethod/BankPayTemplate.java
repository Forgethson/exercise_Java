package designpattern.templatemethod;

public class BankPayTemplate extends AbstractPayTemplate {
    @Override
    protected void doPay(String userId, int amount) {
        System.out.println("银行卡扣款，userId=" + userId + ", amount=" + amount);
    }
}
