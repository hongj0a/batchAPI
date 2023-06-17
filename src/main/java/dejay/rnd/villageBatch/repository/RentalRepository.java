package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Long countAllByActiveYnAndDeleteYnAndUser_userIdx(boolean activeYn, boolean deleteYn, Long userIdx);

    //rental 좋아요수 구해서 user_count 업데이트
    List<Rental> findByUser_userIdxAndActiveYnAndDeleteYn(Long userIdx, boolean activeYn, boolean deleteYn);

}
