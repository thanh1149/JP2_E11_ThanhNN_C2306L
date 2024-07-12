package Service;

import Entity.Account;

public abstract class AccountRepo {
    private Account account;
    private double amount;

    public AccountRepo(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract Account transactionRequest();
    public abstract Account addInterest();
}
