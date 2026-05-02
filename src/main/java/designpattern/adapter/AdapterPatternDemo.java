package designpattern.adapter;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        PayService aliPay = new AliPayAdapter(new AliPaySdk());
        PayService wechatPay = new WechatPayAdapter(new WechatPaySdk());
        PayService bankPay = new BankPayAdapter(new BankPaySdk());

        // 调用方只面向统一的 PayService，不需要关心底层 SDK 的方法名、参数类型和金额单位差异。
        aliPay.pay("USER_001", 100);
        wechatPay.pay("USER_001", 100);
        bankPay.pay("USER_001", 100);
    }
}
