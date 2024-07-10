package Service;

import Entity.Account;
import Generic.BankingGeneric;

import java.util.List;
import java.util.Optional;

public class AccountService implements BankingGeneric<Account, Double> {
//    private Account account;
//    private double amount;
    public static List<Account> accounts;

    public AccountService(){}
//    public AccountService(Account account, double amount){
//        this.account = account;
//        this.amount = amount;
//    }
//
//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public Account transactionRequest() {
//        return null;
//    }

    @Override
    public Account add(Account account) {
        return null;
    }

    @Override
    public Optional<Account> findById(Double id) {
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }
}
