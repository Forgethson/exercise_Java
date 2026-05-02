package designpattern.chainofresponsibility;

public class UserStatusCheckHandler extends PayCheckHandler {
    @Override
    protected boolean doCheck(PayRequest request) {
        if (!request.isUserActive()) {
            System.out.println("用户状态校验失败：用户不可用");
            return false;
        }
        System.out.println("用户状态校验通过");
        return true;
    }
}
