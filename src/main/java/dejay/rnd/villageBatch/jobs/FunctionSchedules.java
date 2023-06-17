package dejay.rnd.villageBatch.jobs;
import dejay.rnd.villageBatch.domain.Alarm;
import dejay.rnd.villageBatch.domain.BatchLog;
import dejay.rnd.villageBatch.domain.Transaction;
import dejay.rnd.villageBatch.domain.TransactionHistory;
import dejay.rnd.villageBatch.repository.*;
import dejay.rnd.villageBatch.util.BatchUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class FunctionSchedules {

    private final UserRepository userRepository;
    private final BatchLogRepository batchLogRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AlarmRepository alarmRepository;

    public FunctionSchedules(UserRepository userRepository, BatchLogRepository batchLogRepository, TransactionRepository transactionRepository, TransactionHistoryRepository transactionHistoryRepository, AlarmRepository alarmRepository) {
        this.userRepository = userRepository;
        this.batchLogRepository = batchLogRepository;
        this.transactionRepository = transactionRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.alarmRepository = alarmRepository;
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
}
