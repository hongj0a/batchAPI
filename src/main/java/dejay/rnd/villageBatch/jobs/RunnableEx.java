package dejay.rnd.villageBatch.jobs;


import dejay.rnd.villageBatch.domain.Notice;
import dejay.rnd.villageBatch.domain.User;
import dejay.rnd.villageBatch.repository.NoticeRepository;
import dejay.rnd.villageBatch.repository.UserRepository;
import dejay.rnd.villageBatch.service.PushService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RunnableEx implements Runnable {

    private long noticeIdx;
    private String noticeType;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final PushService pushRequest;

    public RunnableEx(long noticeIdx, String noticeType, NoticeRepository noticeRepository, UserRepository userRepository, PushService pushRequest) {
        this.noticeIdx = noticeIdx;
        this.noticeType = noticeType;
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
        this.pushRequest = pushRequest;
    }

    @Override
    public void run() {
        try {
            log.info("START run BATCH ... ");
            Notice notice = noticeRepository.findByDeleteYnAndActiveYnAndNoticeIdx(false, true, noticeIdx);
            if ( "1".equals(notice.getNoticeType()) ) {
                if ( notice.isActiveYn() ) {
                    List<User> uLst = userRepository.findAllByMarketingNoticeYnAndStatusNot(true, 30);
                    if ( 0 < uLst.size() ) {
                        Long[] hostIdxes = new Long[uLst.size()];

                        for (int i=0; i<uLst.size(); i++) {
                            hostIdxes[i] = uLst.get(i).getUserIdx();
                        }

                        StringBuilder sb = new StringBuilder();
                        sb.append("[공지사항] ");
                        sb.append(notice.getTitle());

                        String pushTitle = "새로운 공지사항";

                        pushRequest.sendPush(hostIdxes, pushTitle, sb.toString());
                    }
                }
            } else {
                // 이벤트 공지가 아닌 경우
                if ( notice.isActiveYn() ) {

                    StringBuilder sb = new StringBuilder();
                    sb.append("[공지사항] ");
                    sb.append(notice.getTitle());

                    String pushTitle = "새로운 공지사항";
                    // AOS
                    pushRequest.sendPush(null, pushTitle, sb.toString(), "village_android_notice");

                    // IOS
                    pushRequest.sendPush(null, pushTitle, sb.toString(), "village_ios_notice");
                }
            }

            log.info("FINISH BATCH ... ");
        } catch (Exception e) {
            log.info("THREAD SHUTDOWN !!!!!!!!!!!!! ");
            e.printStackTrace();
        } finally {
            log.info("FINALLY ... ");
        }
    }
}
