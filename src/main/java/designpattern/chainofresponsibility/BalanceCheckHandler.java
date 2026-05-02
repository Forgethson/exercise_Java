package designpattern.chainofresponsibility;

public class BalanceCheckHandler extends PayCheckHandler {
    @Override
    protected boolean doCheck(PayRequest request) {
        if (request.getBalance() < request.getAmount()) {
            System.out.println("余额校验失败：余额不足");
            return false;
        }
        System.out.println("余额校验通过");
        return true;
    }
}
