package dejay.rnd.villageBatch.jobs;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dejay.rnd.villageBatch.domain.*;
import dejay.rnd.villageBatch.repository.*;
import dejay.rnd.villageBatch.service.PushService;
import dejay.rnd.villageBatch.util.BatchUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Component
public class FunctionSchedules {

    private final UserRepository userRepository;
    private final BatchLogRepository batchLogRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AlarmRepository alarmRepository;
    private final NoticeRepository noticeRepository;
    private final PushService pushRequest;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private Map<String, ScheduledFuture<?>> jobMap = new HashMap<>();

    public FunctionSchedules(UserRepository userRepository, BatchLogRepository batchLogRepository, TransactionRepository transactionRepository, TransactionHistoryRepository transactionHistoryRepository, AlarmRepository alarmRepository, NoticeRepository noticeRepository, PushService pushRequest) {
        this.userRepository = userRepository;
        this.batchLogRepository = batchLogRepository;
        this.transactionRepository = transactionRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.alarmRepository = alarmRepository;
        this.noticeRepository = noticeRepository;
        this.pushRequest = pushRequest;
    }

    //5분마다 렌탈매칭후 12시간 지나면 자동 취소처리
    @Scheduled(cron = "0 0/5 * * * *")
    public void matchCanceled () throws ParseException {

        /**
         * 1. 오너가 렌탈매칭(status = 20)을 누른 후 12시간이 지났는지 5분마다 검사
         * 2. 12시간이 지났으면 그 거래는 취소로 처리 (status = 10)
         */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

        //12개월 검사
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -12);
        Date before12h = dateFormat.parse(dateFormat.format(cal.getTime()));

        //date12h before transactions
        List<Transaction> trans = transactionRepository.findByOwnerStatusAndStatusAtLessThanEqual(20, before12h);

        for (int i = 0; i < trans.size(); i++) {
            trans.get(i).setCancelYn(true);
            trans.get(i).setCancelAt(BatchUtil.getNowDate());
            trans.get(i).setStatusAt(BatchUtil.getNowDate());

            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setTransactionIdx(trans.get(i).getTransactionIdx());
            transactionHistory.setCancelYn(true);
            transactionHistory.setCancelAt(BatchUtil.getNowDate());
            transactionHistory.setStatusAt(BatchUtil.getNowDate());

            transactionRepository.save(trans.get(i));
            transactionHistoryRepository.save(transactionHistory);
        }


        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("matchCanceled()");
        batchLogRepository.save(batchLog);

    }

    //40일 지난 알람 리스트 삭제
    //매일 오전 2시
    @Scheduled(cron = "0 0 2 * * *")
    public void alarmDeleted () throws ParseException {

        /**
         * 1. 40일 지난 알림리스트 검사해서 삭제
         */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        //12개월 검사
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -40);
        Date before40days = dateFormat.parse(dateFormat.format(cal.getTime()));

        //date40days before alarms
        List<Alarm> findsAlarms = alarmRepository.findAlarmByCreateAtLessThanEqual(before40days);

        for (int i = 0; i < findsAlarms.size(); i++) {
            alarmRepository.delete(findsAlarms.get(i));
        }

        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("alarmDeleted()");
        batchLogRepository.save(batchLog);

    }


    // 0시 0분 0초에 시작 -> 1시간마다 실행
    @Scheduled(cron = "0 0 12/1 * * *")
    public void noticeMethod() throws ParseException {
        // TODO - 공지사항 푸쉬 알림
        // 내용 : [공지사항] {공지사항 제목}
        // 타이틀 : 새로운 공지사항
        // 받는 사람 : 유형이 이벤트일 경우 이벤트 알림(marketing_notice_yn) ON인 사람한테만 보내기. 이벤트 유형이 아니면 토픽으로 전송

        log.info("START testMethod ... ");
        threadPoolTaskScheduler.shutdown();
        threadPoolTaskScheduler.initialize();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

        LocalDateTime date = LocalDateTime.now();
        Date now_date = Timestamp.valueOf(date);
        Date now_date2 = Timestamp.valueOf(date.plusHours(1));
        log.info("now_date is ... [{}]", now_date.toString());
        log.info("LocalDateTime date ... [{}]", date);
        log.info("LocalDateTime date plus 1 hours ... [{}]", date.plusHours(1));

        log.info("dateFormat.format(now_date) is ... [{}]", dateFormat.format(now_date));
        Date dt = dateFormat.parse(dateFormat.format(now_date));

        log.info("dt is ... [{}]", dt.toString());
        List<Notice> nLst = noticeRepository.findAllByPushNowYnAndDeleteYnAndActiveYnAndActiveAtBetween(false, false, true, now_date, now_date2);
        log.info("Notice List size is ... [{}]", nLst.size());

        JsonArray cronArr = new JsonArray();
        nLst.forEach(
                notice -> {
                    log.info("notice Idx is ... [{}]", notice.getNoticeIdx());
                    log.info("notice title is ... [{}]", notice.getTitle());
                    Date activeAt = notice.getActiveAt();

                    LocalDateTime ldt = activeAt.toInstant().atZone(ZoneId.systemDefault())
                            .toLocalDateTime();

                    log.info("ldt : {}", ldt);
                    log.info("getYear : {}", ldt.getYear());
                    log.info("getMonth : {}", ldt.getMonthValue());
                    log.info("getDay : {}", ldt.getDayOfMonth());
                    log.info("getHour : {}", ldt.getHour());
                    log.info("getMinutes : {}", ldt.getMinute());
                    log.info("getSeconds : {}", ldt.getSecond());
                    String year = String.valueOf(ldt.getYear()).substring(2);
                    String month = String.valueOf(ldt.getMonthValue());
                    String day = String.valueOf(ldt.getDayOfMonth());
                    String hour = String.valueOf(ldt.getHour());
                    String minute = String.valueOf(ldt.getMinute());
                    String second = String.valueOf(ldt.getSecond());

                    JsonObject jObj = new JsonObject();
                    jObj.addProperty("noticeIdx", notice.getNoticeIdx());
                    jObj.addProperty("noticeType", notice.getNoticeType());
                    jObj.addProperty("year", year);
                    jObj.addProperty("month", month);
                    jObj.addProperty("day", day);
                    jObj.addProperty("hour", hour);
                    jObj.addProperty("minute", minute);
                    jObj.addProperty("second", second);

                    cronArr.add(jObj);
                }
        );

        this.scheduleMethod(cronArr);

        BatchLog batchLog = new BatchLog();

        batchLog.setMethod("static noticeMethod()");
        batchLogRepository.save(batchLog);
    }


    public void scheduleMethod(JsonArray jArr) {
        log.info("Enter exampleMethod ... ");
        threadPoolTaskScheduler.setPoolSize(10);



        if ( 0 < jArr.size() ) {
            ScheduledFuture<?> scheduledFuture;

            for (int i=0; i<jArr.size(); i++) {

                JsonObject tmpObj = new JsonObject();
                tmpObj = jArr.get(i).getAsJsonObject();

                RunnableEx runnableEx = new RunnableEx(tmpObj.get("noticeIdx").getAsLong(), tmpObj.get("noticeType").getAsString(), noticeRepository, userRepository, pushRequest, batchLogRepository);

                StringBuilder sb = new StringBuilder();

                sb.append(tmpObj.get("second").getAsString());
                sb.append(" ");
                sb.append(tmpObj.get("minute").getAsString());
                sb.append(" ");
                sb.append(tmpObj.get("hour").getAsString());
                sb.append(" ");
                sb.append(tmpObj.get("day").getAsString());
                sb.append(" ");
                sb.append(tmpObj.get("month").getAsString());
                sb.append("  ?");
                //sb.append(tmpObj.get("year").getAsString());

                log.info("cron !!!!! ......... [{}]", sb.toString());

                scheduledFuture = threadPoolTaskScheduler.schedule(runnableEx, new CronTrigger(sb.toString()));
            }
        }



        //scheduledFuture = threadPoolTaskScheduler.schedule(runnableEx, new CronTrigger("0 0 15 * ?"));
        //jobMap.put("testId", scheduledFuture);
        //threadPoolTaskScheduler.shutdown();
    }
}
