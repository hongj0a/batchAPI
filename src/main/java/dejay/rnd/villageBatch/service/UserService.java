package dejay.rnd.villageBatch.service;

import dejay.rnd.villageBatch.domain.User;
import dejay.rnd.villageBatch.repository.UserRepository;
import dejay.rnd.villageBatch.util.BatchUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        user.setUpdateAt(BatchUtil.getNowDate());
        userRepository.save(user);
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
        user.setUpdateAt(BatchUtil.getNowDate());
        userRepository.save(user);
    }

}
