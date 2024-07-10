package Service;

import Entity.Customer;
import Generic.BankingGeneric;

import java.util.List;
import java.util.Optional;

public class CustomerService implements BankingGeneric<Customer, Integer> {
    public static List<Customer> customers;
    public CustomerService() {}

    @Override
    public Customer add(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }
}
