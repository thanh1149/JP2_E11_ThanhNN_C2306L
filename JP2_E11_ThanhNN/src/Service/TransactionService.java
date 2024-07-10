package Service;

import Entity.Transaction;
import Generic.BankingGeneric;

import java.util.List;
import java.util.Optional;

public class TransactionService implements BankingGeneric<Transaction,Integer> {
    public static List<Transaction> transactions;

    @Override
    public Transaction add(Transaction transaction) {
        return null;
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        return List.of();
    }
}
