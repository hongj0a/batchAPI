package dejay.rnd.villageBatch.util;

import com.google.gson.JsonObject;
import dejay.rnd.villageBatch.dto.PushDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Slf4j
public class BatchUtil {

    public static Date getNowDate() {
        LocalDateTime date = LocalDateTime.now();
        Date now_date = Timestamp.valueOf(date);

        return now_date;
    }


    public static void pushRequest(PushDto pushDto) {
        /**
         * 파라미터 정리
         * user --> 알림 보내는 쪽 유저 정보
         * hostIdx[] 알림 받는 쪽 유저 리스트
         * target_idx --> 게시글 상세페이지 이동을 위한 idx
         * message --> string으로 만들어서 보냄
         * < push type definition >
         *  0  : 상세로 이동이 필요 없는 알람
         *  10 : 렌탈게시글
         *  20 : 후기게시글
         *  30 : 일대일문의 게시글
         *  40 : 공지사항 게시글
         *  50 : 채팅방 상세
         *  60 : 이의신청 게시글
         */
        RestTemplate rt = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        params.add("userIdx", null);
        params.add("adminIdx", null);
        params.add("targetIdx", "0");
        params.add("message", pushDto.getMessage());
        params.add("type", String.valueOf(pushDto.getType()));
        params.add("title", pushDto.getTitle());

        if ( null != pushDto.getHostIdxes() ) {
            for (int i=0; i<pushDto.getHostIdxes().length; i++) {
                params.add("hostIdxes", String.valueOf(pushDto.getHostIdxes()[i]));
            }
        } else {
            params.add("hostIdxes", null);
        }
        params.add("topicType", pushDto.getTopicType());

        String url = "http://192.168.1.242:7070/fcm/send";

        JsonObject response = rt.postForObject(
                url,
                entity,
                JsonObject.class
        );

        log.info("response ::: {}", response);

    }

}
