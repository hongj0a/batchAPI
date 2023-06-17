package dejay.rnd.villageBatch.service;

import dejay.rnd.villageBatch.domain.User;
import dejay.rnd.villageBatch.domain.UserCount;
import dejay.rnd.villageBatch.repository.UserCountRepository;
import dejay.rnd.villageBatch.util.BatchUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCountService {

   private final UserCountRepository userCountRepository;

    public UserCountService(UserCountRepository userCountRepository) {
        this.userCountRepository = userCountRepository;
    }

    @Transactional
    public void updateCnt(UserCount userCount, Long cnt) {
        UserCount findCount = userCountRepository.findByUser_UserIdx(userCount.getUser().getUserIdx());
        findCount.setAllLikeCnt(cnt);
        findCount.setUpdateAt(BatchUtil.getNowDate());
    }

    @Transactional
    public void save(UserCount userCount, Long cnt) {
        userCount.setAllLikeCnt(cnt);
        userCountRepository.save(userCount);
    }
}
