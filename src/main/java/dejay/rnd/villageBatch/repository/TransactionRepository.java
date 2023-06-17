package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOwnerStatusAndStatusAtLessThanEqual(Integer status, Date statusAt);
}
