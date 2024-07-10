package Generic;

import java.util.List;
import java.util.Optional;

public interface BankingGeneric<T,ID>{
    T add(T t);
    Optional<T> findById(ID id);
    T update(T t);
    List<T> findAll();
}
