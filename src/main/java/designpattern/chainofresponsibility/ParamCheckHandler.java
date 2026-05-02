package designpattern.chainofresponsibility;

public class ParamCheckHandler extends PayCheckHandler {
    @Override
    protected boolean doCheck(PayRequest request) {
        if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            System.out.println("参数校验失败：userId 不能为空");
            return false;
        }
        if (request.getAmount() <= 0) {
            System.out.println("参数校验失败：amount 必须大于 0");
            return false;
        }
        System.out.println("参数校验通过");
        return true;
    }
}
