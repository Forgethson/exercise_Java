package designpattern.adapter;

/**
 * 微信适配器：把统一的 PayService 调用转换成微信 SDK 调用。
 */
public class WechatPayAdapter implements PayService {
    private final WechatPaySdk wechatPaySdk;

    public WechatPayAdapter(WechatPaySdk wechatPaySdk) {
        this.wechatPaySdk = wechatPaySdk;
    }

    @Override
    public void pay(String userId, int amount) {
        int cents = amount * 100;
        wechatPaySdk.wxPay(userId, cents);
    }
}
