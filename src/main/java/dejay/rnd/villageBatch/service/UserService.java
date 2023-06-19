package dejay.rnd.villageBatch.service;

import dejay.rnd.villageBatch.domain.StatusHistory;
import dejay.rnd.villageBatch.domain.User;
import dejay.rnd.villageBatch.repository.StatusHistoryRepository;
import dejay.rnd.villageBatch.repository.UserRepository;
import dejay.rnd.villageBatch.util.BatchUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    public UserService(UserRepository userRepository, StatusHistoryRepository statusHistoryRepository) {
        this.userRepository = userRepository;
        this.statusHistoryRepository = statusHistoryRepository;
    }

    @Transactional
    public void updateUserLevel(User user, int totalScore, Long userLevel) {
        user.setActivityScore(Math.toIntExact(totalScore));
        user.setUserLevel(userLevel);
        user.setUpdateAt(BatchUtil.getNowDate());
        userRepository.save(user);
    }

    @Transactional
    public void updateUserStatus(User user, int status) {
        user.setStatus(status);
        user.setDormancyAt(BatchUtil.getNowDate());
        user.setUpdateAt(BatchUtil.getNowDate());
        userRepository.save(user);

        StatusHistory sh = new StatusHistory();
        sh.setUser(user);
        sh.setDormancyAt(BatchUtil.getNowDate());
        sh.setCreateAt(BatchUtil.getNowDate());
        sh.setStatus(20);
    }

    @Transactional
    public void updateOutUser(User user, int status) {
        user.setStatus(status);
        user.setEmail(null);
        user.setIdEmail(null);
        user.setSnsName(null);
        user.setSnsType(null);
        user.setName(null);
        user.setNickName(null);
        user.setCiValue(null);
        user.setDeleteAt(BatchUtil.getNowDate());
        user.setUpdateAt(BatchUtil.getNowDate());
        userRepository.save(user);

        StatusHistory sh = new StatusHistory();
        sh.setUser(user);
        sh.setDeleteAt(BatchUtil.getNowDate());
        sh.setCreateAt(BatchUtil.getNowDate());
        sh.setStatus(30);

        statusHistoryRepository.save(sh);
    }

}
