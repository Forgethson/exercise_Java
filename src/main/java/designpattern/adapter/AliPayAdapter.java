package designpattern.adapter;

import java.math.BigDecimal;

/**
 * 支付宝适配器：把统一的 PayService 调用转换成支付宝 SDK 调用。
 */
public class AliPayAdapter implements PayService {
    private final AliPaySdk aliPaySdk;

    public AliPayAdapter(AliPaySdk aliPaySdk) {
        this.aliPaySdk = aliPaySdk;
    }

    @Override
    public void pay(String userId, int amount) {
        aliPaySdk.aliPay(userId, BigDecimal.valueOf(amount));
    }
}
