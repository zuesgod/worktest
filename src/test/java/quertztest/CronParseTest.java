package quertztest;

import org.junit.jupiter.api.Test;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * cron表达式解析测试
 *
 * @author zeus
 * @date 2023-08-26 9:26
 **/
public class CronParseTest {

    @Test
    public void test1(){
        String cronExpression = "0 0/30 * * * ?";
        System.out.println("CalculateCronInterval(cronExpression) = " + CalculateCronInterval(cronExpression));

    }

    private static Long CalculateCronInterval(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);

            Date now = new Date();
            Date nextExecution = cron.getNextValidTimeAfter(now);

            LocalDateTime nowLocalDateTime = LocalDateTime.ofInstant(now.toInstant(), cron.getTimeZone().toZoneId());
            LocalDateTime nextExecutionLocalDateTime = LocalDateTime.ofInstant(nextExecution.toInstant(), cron.getTimeZone().toZoneId());

            System.out.println("Cron表达式：" + cronExpression);
            System.out.println("当前时间：" + nowLocalDateTime.toLocalTime());
            System.out.println("下次执行时间：" + nextExecutionLocalDateTime.toLocalTime());
            Duration duration = Duration.between(nowLocalDateTime, nextExecutionLocalDateTime);
            return duration.getSeconds();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
