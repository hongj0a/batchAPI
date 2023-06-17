package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}
