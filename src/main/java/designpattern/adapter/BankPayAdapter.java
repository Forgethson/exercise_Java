package designpattern.adapter;

/**
 * 银行卡适配器：把统一的 PayService 调用转换成银行卡 SDK 调用。
 */
public class BankPayAdapter implements PayService {
    private final BankPaySdk bankPaySdk;

    public BankPayAdapter(BankPaySdk bankPaySdk) {
        this.bankPaySdk = bankPaySdk;
    }

    @Override
    public void pay(String userId, int amount) {
        bankPaySdk.bankPay(new BankCard(userId), amount);
    }
}
