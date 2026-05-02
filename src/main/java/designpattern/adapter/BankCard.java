package designpattern.adapter;

public class BankCard {
    private final String userId;

    public BankCard(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
