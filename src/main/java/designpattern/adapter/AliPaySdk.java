package designpattern.adapter;

import java.math.BigDecimal;

/**
 * 模拟支付宝 SDK：底层接口使用 BigDecimal 表示金额。
 */
public class AliPaySdk {
    public void aliPay(String uid, BigDecimal amount) {
        System.out.println("支付宝 SDK 支付，uid=" + uid + ", amount=" + amount);
    }
}
