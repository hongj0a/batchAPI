package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findAlarmByCreateAtLessThanEqual(Date createAt);
}
