package dejay.rnd.villageBatch.repository;

import dejay.rnd.villageBatch.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByDeleteYnAndActiveYnAndActiveAtBetween(boolean deleteYn, boolean activeYn, Date sTime, Date eTime);

    Notice findByDeleteYnAndActiveYnAndNoticeIdx(boolean deleteYn, boolean activeYn, long noticeIdx);
}
