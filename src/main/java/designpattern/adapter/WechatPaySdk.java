package designpattern.adapter;

/**
 * 模拟微信 SDK：底层接口使用 openId 和分为单位的金额。
 */
public class WechatPaySdk {
    public void wxPay(String openId, int cents) {
        System.out.println("微信 SDK 支付，openId=" + openId + ", cents=" + cents);
    }
}
