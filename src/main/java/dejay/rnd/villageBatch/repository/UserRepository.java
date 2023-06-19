package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    //List<User> findAll();

    User findByUserIdx(Long userIdx);

    List<User> findByLastLoginDateLessThanEqualAndStatusNotIn(Date beforeDate,int[] status);
    List<User> findByStatusAndLastLoginDateLessThanEqual(Integer status,  Date beforeDate );
}
