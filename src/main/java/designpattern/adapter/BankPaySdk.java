package designpattern.adapter;

/**
 * 模拟银行卡 SDK：底层接口需要 BankCard 对象和元为单位的金额。
 */
public class BankPaySdk {
    public void bankPay(BankCard card, int yuan) {
        System.out.println("银行卡 SDK 支付，userId=" + card.getUserId() + ", yuan=" + yuan);
    }
}
