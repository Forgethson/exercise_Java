package designpattern.chainofresponsibility;

public class ChainPatternDemo {
    public static void main(String[] args) {
        PayCheckHandler chain = buildChain();

        PayRequest successRequest = new PayRequest("USER_001", 100, true, 500);
        PayRequest failRequest = new PayRequest("USER_002", 2000, true, 100);

        System.out.println("成功请求校验结果：" + chain.check(successRequest));
        System.out.println("失败请求校验结果：" + chain.check(failRequest));
    }

    private static PayCheckHandler buildChain() {
        PayCheckHandler paramCheck = new ParamCheckHandler();
        PayCheckHandler userStatusCheck = new UserStatusCheckHandler();
        PayCheckHandler riskCheck = new RiskCheckHandler();
        PayCheckHandler balanceCheck = new BalanceCheckHandler();

        // 多个处理器排成一条链，请求会按顺序依次经过每个处理器。
        paramCheck
                .setNext(userStatusCheck)
                .setNext(riskCheck)
                .setNext(balanceCheck);

        return paramCheck;
    }
}
