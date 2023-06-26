package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    //List<User> findAll();

    User findByUserIdx(Long userIdx);

    List<User> findByLastLoginDateLessThanEqualAndStatusNotIn(Date beforeDate,int[] status);

    List<User> findByDeleteAtGreaterThanEqualAndStatus(Date afterDate,int status);
    List<User> findByStatusAndLastLoginDateLessThanEqual(Integer status,  Date beforeDate );

    // 공지사항 푸시 받을 user
    List<User> findAllByMarketingNoticeYnAndStatusNot(boolean marketingNoticeYn, Integer status);
}
