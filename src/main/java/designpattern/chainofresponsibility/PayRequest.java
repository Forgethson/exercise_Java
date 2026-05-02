package designpattern.chainofresponsibility;

public class PayRequest {
    private final String userId;
    private final int amount;
    private final boolean userActive;
    private final int balance;

    public PayRequest(String userId, int amount, boolean userActive, int balance) {
        this.userId = userId;
        this.amount = amount;
        this.userActive = userActive;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isUserActive() {
        return userActive;
    }

    public int getBalance() {
        return balance;
    }
}
