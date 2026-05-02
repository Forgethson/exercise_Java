package designpattern.chainofresponsibility;

/**
 * 抽象处理器：持有下一个处理器，并负责把请求沿链路继续传递。
 */
public abstract class PayCheckHandler {
    private PayCheckHandler next;

    public PayCheckHandler setNext(PayCheckHandler next) {
        this.next = next;
        return next;
    }

    public final boolean check(PayRequest request) {
        if (!doCheck(request)) {
            return false;
        }
        if (next == null) {
            return true;
        }
        return next.check(request);
    }

    protected abstract boolean doCheck(PayRequest request);
}
