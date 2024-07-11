package Service;

import Entity.Account;
import Generic.BankingGeneric;

import java.util.List;
import java.util.Optional;

public class AccountService implements BankingGeneric<Account, Double> {
    public static List<Account> accounts;

    public AccountService(){}

    public boolean checkBalance(Account account, double amount){
        return account.getBalance() >= amount;
    }

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
