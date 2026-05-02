package designpattern.templatemethod;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        AbstractPayTemplate aliPay = new AliPayTemplate();
        AbstractPayTemplate wechatPay = new WechatPayTemplate();
        AbstractPayTemplate bankPay = new BankPayTemplate();

        aliPay.pay("USER_001", 100);
        wechatPay.pay("USER_002", 200);
        bankPay.pay("USER_003", 300);
    }
}
