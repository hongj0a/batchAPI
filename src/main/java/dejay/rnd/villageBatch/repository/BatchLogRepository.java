package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.BatchLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BatchLogRepository extends JpaRepository<BatchLog, Long> {
}
