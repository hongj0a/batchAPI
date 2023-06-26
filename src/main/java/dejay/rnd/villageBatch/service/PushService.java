package dejay.rnd.villageBatch.service;

import dejay.rnd.villageBatch.dto.PushDto;
import dejay.rnd.villageBatch.util.BatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PushService {

    public void sendPush(Long[] hostIdxes, String title, String message) {
        PushDto pushDto = new PushDto();

        pushDto.setHostIdxes(hostIdxes);
        pushDto.setTitle(title);
        pushDto.setMessage(message);

        BatchUtil.pushRequest(pushDto);
    }

    public void sendPush(Long[] hostIdxes, String title, String message, String topicType) {
        PushDto pushDto = new PushDto();

        pushDto.setHostIdxes(hostIdxes);
        pushDto.setTitle(title);
        pushDto.setMessage(message);
        pushDto.setTopicType(topicType);

        BatchUtil.pushRequest(pushDto);
    }
}
