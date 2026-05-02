package designpattern.chainofresponsibility;

public class RiskCheckHandler extends PayCheckHandler {
    @Override
    protected boolean doCheck(PayRequest request) {
        if (request.getAmount() > 10000) {
            System.out.println("风控校验失败：支付金额过大");
            return false;
        }
        System.out.println("风控校验通过");
        return true;
    }
}
