package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByGradeScoreLessThanEqualOrderByGradeScoreDesc(Long gradeScore);
}
