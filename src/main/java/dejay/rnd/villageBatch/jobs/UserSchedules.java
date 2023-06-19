package dejay.rnd.villageBatch.jobs;
import dejay.rnd.villageBatch.domain.*;
import dejay.rnd.villageBatch.repository.*;
import dejay.rnd.villageBatch.service.PushService;
import dejay.rnd.villageBatch.service.UserCountService;
import dejay.rnd.villageBatch.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class UserSchedules {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final UserCountRepository userCountRepository;
    private final UserCountService userCountService;
    private final GradeRepository gradeRepository;
    private final UserService userService;
    private final BatchLogRepository batchLogRepository;
    private final PushService pushService;

    public UserSchedules(UserRepository userRepository, RentalRepository rentalRepository, UserCountRepository userCountRepository, UserCountService userCountService, GradeRepository gradeRepository, UserService userService, BatchLogRepository batchLogRepository, PushService pushService) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.userCountRepository = userCountRepository;
        this.userCountService = userCountService;
        this.gradeRepository = gradeRepository;
        this.userService = userService;
        this.batchLogRepository = batchLogRepository;
        this.pushService = pushService;
    }

    /*public void allLikeSetting () {

        List<User> users = userRepository.findAll();

        System.out.println("users.size() = " + users.size());


        for (int i = 0; i < users.size(); i++) {
            //Long rentalCount = rentalRepository.countAllByActiveYnAndDeleteYnAndUser_userIdx(true, false, users.get(i).getUserIdx());

            List<Rental> findLikeCnt = rentalRepository.findByUser_userIdxAndActiveYnAndDeleteYn(users.get(i).getUserIdx(), true, false);
            //UserCount userCount = userCountRepository.findByUser_UserIdx(users.get(i).getUserIdx());


            if (findLikeCnt.size() !=0 ) {
                for (int j = 0; j < findLikeCnt.size(); j++) {
                    UserCount userCount = userCountRepository.findByUser_UserIdx(users.get(i).getUserIdx());

                    if (userCount != null) {
                        userCountService.updateCnt(userCount, Long.valueOf(findLikeCnt.get(j).getLikeCnt()) + userCount.getAllLikeCnt());
                    } else {
                        UserCount newCount = new UserCount();
                        newCount.setUser(users.get(i));
                        if (findLikeCnt.size() != 0) {
                            for (int k = 0; k < findLikeCnt.size(); k++) {
                                userCountService.save(newCount, Long.valueOf(findLikeCnt.get(k).getLikeCnt()));
                            }
                        }
                    }
                }

            }

        }

    }*/

    //매일 오후 11시 50분 (0 50 23 * * *)
    @Scheduled(cron = "0 50 23 * * *")
    public void scoring () {

        Long totalScore=0L;
        Long finalUserLevel=0L;

        List<User> users = userRepository.findAll();
        System.out.println("users.size() = " + users.size());

        /**
         * 1. 전체유저 검색
         * 2. 전체유저리스트 만큼 forEach
         * 3. user_idx로 추출된 유저의 활동내역을 기반으로 검사
         * 4. 게시글수, 조회수, 좋아요받은수, 남긴후기수
         * 5. 활동점수 = (게시물겟수 * 5) + (조회수 / 6) + (후기수 * 2) + (좋아요수 / 4)
         */
        for (int i = 0; i < users.size(); i++) {
            Long rentalCount = rentalRepository.countAllByActiveYnAndDeleteYnAndUser_userIdx(true, false, users.get(i).getUserIdx());
            User findUser = userRepository.findByUserIdx(users.get(i).getUserIdx());

            UserCount uc = userCountRepository.findByUser_UserIdx(users.get(i).getUserIdx());
            if (uc != null) {
                totalScore = (rentalCount * 5) + (uc.getViewCnt() / 6) + (uc.getAllLikeCnt() / 4) + (uc.getReceiveReviewCnt() * 2);

                List<Grade> findLevel = gradeRepository.findByGradeScoreLessThanEqualOrderByGradeScoreDesc(totalScore);

                if (findLevel.size() != 0) {
                    finalUserLevel = Long.valueOf(findLevel.get(0).getGradeIdx());
                    userService.updateUserLevel(findUser, Math.toIntExact(totalScore), finalUserLevel);
                }

            }

        }

        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("Scoring()");
        batchLogRepository.save(batchLog);
    }

    //매일 오전 12시 유예기간 30일 전이라면 휴면계정(11개월) || 탈퇴게정(59개월) 알림
    @Scheduled(cron = "0 0 0 * * *")
    public void statusChangeAlarm () throws ParseException {

        /**
         * 1. 마지막 로그인날짜가 오늘날짜기준 11개월 전이라면 휴면계정 전환 알림 푸시 날리기
         * 2. 마지막 로그인날짜가 오늘날짜기준 59개월 전이라면 탈퇴계정 전환 알림 푸시 날리기
         */

        Executor executor = Executors.newFixedThreadPool(30);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        //11개월 검사
        Calendar cal11 = Calendar.getInstance();
        cal11.add(Calendar.MONTH, -11);
        Date before11 = dateFormat.parse(dateFormat.format(cal11.getTime()));

        //59개월 검사
        Calendar cal59 = Calendar.getInstance();
        cal59.add(Calendar.MONTH, -59);
        Date before59 = dateFormat.parse(dateFormat.format(cal59.getTime()));

        //date11 users
        List<User> users11monthBefore = userRepository.findByLastLoginDateLessThanEqualAndStatusNotIn(before11, new int[]{30});
        Long[] host11s = new Long[users11monthBefore.size()];

        //date59 users
        List<User> users59monthBefore = userRepository.findByLastLoginDateLessThanEqualAndStatusNotIn(before59, new int[]{30});
        Long[] host59s = new Long[users59monthBefore.size()];


        for (int j = 0; j < users11monthBefore.size(); j++) {
            for (int k = 0; k < host11s.length; k++) {
                host11s[k] = users11monthBefore.get(k).getUserIdx();
            }
        }

        for (int l = 0; l < users59monthBefore.size(); l++) {
            for (int m = 0; m < host59s.length; m++) {
                host59s[m] = users59monthBefore.get(m).getUserIdx();
            }
        }

        CompletableFuture.runAsync(() -> {
            try {
                pushService.sendPush(host11s,
                        "휴면 계정 등록 안내", "회원님께서는 1년 동안 접속하지 않아 휴면계정으로 전환 될 예정입니다. 1개월 이내 재 로그인 시 휴면계정으로 전환되지 않으며, 휴면계정이 되더라도 다시 로그인하시면 계정이 활성화됩니다. ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": hi");
        }, executor);

        CompletableFuture.runAsync(() -> {
            try {
                pushService.sendPush(host59s,
                        "장기 미접속으로 인한 탈퇴처리 안내", "회원님께서는 5년동안 접속하지 않아 자동탈퇴 처리 예정입니다. 자동탈퇴 처리 후 재가입이 불가능 합니다. 서비스 이용에 참고해 주세요.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": hi");
        }, executor);

        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("statusChangeAlarm");
        batchLogRepository.save(batchLog);

    }

    //매일 오전 1시 휴면계정(12개월) || 탈퇴계정(60개월) 전환처리
    @Scheduled(cron = "0 0 1 * * *")
    public void statusChanged () throws ParseException {

        /**
         * 1. 마지막 로그인날짜가 오늘날짜기준 12개월 전이라면 휴면계정(status = 20) 전환하기
         * 2. 마지막 로그인날짜가 오늘날짜기준 60개월 전이라면 탈퇴게정(status = 30) 전환하기
         * 3. 탈퇴 전환시 email, snsType, snsName, ciValue null로 업데이트
         */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        //12개월 검사
        Calendar cal12 = Calendar.getInstance();
        cal12.add(Calendar.YEAR, -1);
        Date before12 = dateFormat.parse(dateFormat.format(cal12.getTime()));

        //date12 users
        List<User> users12monthBefore = userRepository.findByLastLoginDateLessThanEqualAndStatusNotIn(before12, new int[]{30});

        //60개월 검사
        Calendar cal60 = Calendar.getInstance();
        cal60.add(Calendar.YEAR, -5);
        Date before60 = dateFormat.parse(dateFormat.format(cal60.getTime()));

        //date60 users
        List<User> users60monthBefore = userRepository.findByLastLoginDateLessThanEqualAndStatusNotIn(before60, new int[]{30});

        for (int o = 0; o < users12monthBefore.size(); o++) {
            userService.updateUserStatus(users12monthBefore.get(o), 20);
        }

        for (int i = 0; i < users60monthBefore.size(); i++) {
            userService.updateOutUser(users60monthBefore.get(i), 30);
        }

        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("statusChanged()");
        batchLogRepository.save(batchLog);

    }

    //매일 오전 3시 탈퇴 후 3년지난 회원 개인정보 지우기
    @Scheduled(cron = "0 0 3 * * *")
    public void personalInfoDeleted () throws ParseException {

        /**
         * 1. deleteAt 이후 36개월 경과시 개인정보 모두 삭제 처리
         */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        //12개월 검사
        Calendar cal36 = Calendar.getInstance();
        cal36.add(Calendar.YEAR, 3);
        Date after36 = dateFormat.parse(dateFormat.format(cal36.getTime()));

        //date12 users
        List<User> users36monthAfter = userRepository.findByDeleteAtGreaterThanEqualAndStatus(after36, 30);


        for (int o = 0; o < users36monthAfter.size(); o++) {
            userService.updateRemoveUser(users36monthAfter.get(o));
        }


        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("personalInfoDeleted()");
        batchLogRepository.save(batchLog);

    }
}
