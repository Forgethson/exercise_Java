package designpattern.facade;

public class RiskService {
    public void check(String userId, int amount) {
        System.out.println("风控检查通过，userId=" + userId + ", amount=" + amount);
    }
}
