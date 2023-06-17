package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.UserCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCountRepository extends JpaRepository<UserCount, Long> {

    UserCount findByUser_UserIdx(Long userIdx);
}
