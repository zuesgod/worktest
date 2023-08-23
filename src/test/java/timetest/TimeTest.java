package timetest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import entity.timeTest.DataHistoryMini;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * 时间测试类
 *
 * @author zeus
 * @date 2022-01-14 15:48
 **/
public class TimeTest {

    /***
     * 测试日、周、月、季、年对应时间的定时任务的时间比较
     * @return void
     * @author zeus
     * @date 2022/1/14 15:51
     */
    @Test
    public void test1() {
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String now1 = LocalDateTime.now(ZoneId.of("Asia/Shanghai")).format(timeFormatter);
        //System.out.println("now1 = " + now1);
        //获取当地(上海)时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("now = " + now);
        //带着时区(默认时区,本地日期)
        ZonedDateTime zonedDateTime = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        //1.zonedDateTime.toInstant()转换成时间戳(因为zonedDateTime包含了时区和时间信息,可以转换为GMT的时间戳(时间+时区))2.Date.from会使用默认时区转换时间戳为本地时间(默认时区)
//        Date date = Date.from(zonedDateTime.toInstant());
        System.out.println("zonedDateTime.getHour() = " + zonedDateTime.getHour());
        System.out.println("zonedDateTime.getMinute() = " + zonedDateTime.getMinute());

        //时分秒
        System.out.println("now.getHour() = " + now.getHour());
        System.out.println("now.getMinute() = " + now.getMinute());


        //获取当前天

        //获取当前周中的哪一天
        System.out.println("now.getDayOfWeek() = " + now.getDayOfWeek());
        //获取当前月中的哪一天
        System.out.println("now.getDayOfMonth() = " + now.getDayOfMonth());
        //获取当前季中的哪一天

        //获取当前年中的哪一天
        System.out.println("now.getDayOfYear() = " + now.getDayOfYear());

        //查询所有已启用的工单id与生成频率数据
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
//        LocalDateTime dateTime = LocalDateTime.parse(date, dateFormatter);
//        System.out.println("dateTime = " + dateTime);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[['T'HH][:mm][:ss]]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .toFormatter();
        LocalDateTime localDateTime = LocalDateTime.parse("2022-01-02", formatter);
//        System.out.println("localDateTime = " + localDateTime);
        boolean quarter = getYear(localDateTime);
        System.out.println("是否为其年的第一天 = " + quarter);
    }

    @Test
    public void test2(){
        Map<String, Long> timeMap = setFilterTime();
        System.out.println("timeMap.get(\"startTime\") = " + timeMap.get("startTime"));
        System.out.println("timeMap.get(\"endTime\") = " + timeMap.get("endTime"));
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeMap.get("startTime"));
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeMap.get("endTime"));
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
    }
    private Map<String, Long> setFilterTime(){
        Map<String, Long> timeMap = new HashMap<>();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        //获取昨日时间对象
        LocalDateTime yesterDay = now.minusDays(1);
        //明日时间对象
        LocalDateTime tomorrow = now.plusDays(1);
        LocalDateTime tempDay = null;
        //昨日23:59:59的时间戳 yesterDayTwentyThreeTime
        tempDay = yesterDay.withHour(23).withMinute(59).withSecond(59);
        long yesterDayTwentyThreeTime = tempDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //昨日20:30分的时间戳  yesterDayTwentyTime
        tempDay = yesterDay.withHour(20).withMinute(30).withSecond(0);
        long yesterDayTwentyTime = tempDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //当天20:30分的时间戳   todayTwentyTime
        tempDay = now.withHour(20).withMinute(30).withSecond(0);
        long todayTwentyTime = tempDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //当天8:30的时间戳    todayEightTime
        tempDay = now.withHour(8).withMinute(30).withSecond(0);
        long todayEightTime = tempDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //明日00:00:00的时间戳 tomorrowZeroTime
        tempDay = tomorrow.withHour(0).withMinute(0).withSecond(0);
        long tomorrowZeroTime = tempDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //明日8:30的时间戳    tomorrowEightTime
        tempDay = tomorrow.withHour(8).withMinute(30).withSecond(0);
        long tomorrowEightTime = tempDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        long currentTime = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        //默认为当天的时间段
        Long startTime = todayEightTime;
        Long endTime = todayTwentyTime;

        //如果时间范围在当天20:30-00:00 需要设置endTime需要加一天，为明日8:30 startTime为当天20:30
        //当前时间戳大于当天20:30分的时间戳 todayTwentyTime 小于明日00:00:00的时间戳 tomorrowZeroTime
        if(currentTime > todayTwentyTime && currentTime < tomorrowZeroTime){
            startTime = todayTwentyTime;
            endTime = tomorrowEightTime;
        }

        //如果时间范围在当天00:00-08:30 当前时间戳小于当天8:30的时间戳 todayEightTime，大于前一天23:59:59的时间戳 yesterDayTwentyThreeTime
        if(currentTime > yesterDayTwentyThreeTime && currentTime < todayEightTime){
            startTime = yesterDayTwentyTime;
            endTime = todayEightTime;
        }
        timeMap.put("startTime",startTime);
        timeMap.put("endTime",endTime);
        return timeMap;
    }

    /**
     * 测试获取指定字符串的时间时间戳
     * @author zeus
     * @date 2022/10/9 14:20
     */
    @Test
    public void test3(){
//        String time = "2022-9-3 2:13:42";
        String time = "2022-09-20 00:13:42";
        String transTime = getTransTime(time);
        System.out.println("transTime = " + transTime);

//        Long ts = stringToMilli(time);
//        System.out.println("ts = " + ts);
    }

    //设置指定时间字符串的日期 + 1天
    @Test
    public void test4(){
        String date = "2022/10/21";
        //偏移天
        String dateStr2 = "2021-05-16 22:50:34";
        DateTime date2 = DateUtil.parse(date);
        DateTime dateTime = DateUtil.offsetDay(date2,1);
        System.out.println("dateTime = " + dateTime);
        final String format = new SimpleDateFormat("yyyy/M/d").format(dateTime);
        System.out.println("format = " + format);
    }

    //判断当前时间与指定时间戳的时间有无超过30天
    @Test
    public void test5(){
        final Date date = new Date(1666281600000L);
        final String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("format = " + format);
        final boolean checkOverdue = checkOverdue(date.getTime(), new Date().getTime(),30);
        System.out.println("checkOverdue = " + checkOverdue);
    }

    /**
     * 输入两个时间，一个是开始时间，另一个是结束时间
     * 两者相比较，判断是不是超过指定天数
     * @param startTime 开始时间戳
     * @param endTime 结束时间戳
     * @param dayNum 指定天数
     * @return
     */
    public boolean checkOverdue(Long startTime ,Long endTime,int dayNum){
        if(ObjectUtil.isNull(startTime) || ObjectUtil.isNull(endTime)){
            throw new RuntimeException("参数非法！");
        }
        long day = 60*1000*60*24; // 1天
        if(endTime-(day*dayNum) > startTime){
            return true;
        }else{
            return false;
        }
    }

    public String getTransTime(String timeStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
        LocalDateTime date = LocalDateTime.parse(timeStr, df);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String transTime = f2.format(date);
        return transTime;
    }


    /**
     * 获取指定字符串的时间时间戳
     * @param time 时间字符串
     * @return java.lang.Long
     * @author zeus
     * @date 2022/10/9 14:31
     */
    private Long stringToMilli(String time){
        //时间校验
        Assert.notBlank(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter ftf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = null;
        try{
            parse = LocalDateTime.parse(time, ftf);
            String format = ftf2.format(parse);
            System.out.println("format = " + format);
            parse = LocalDateTime.parse(format, ftf);
        }catch (Exception e){
            throw new RuntimeException("时间格式错误！");
        }
        //转换时间戳
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断当前时间是否为其季度的第一天
     */
    public static boolean getQuarter(LocalDateTime time) {
        int month = time.getMonth().getValue();
        int dayOfMonth = time.getDayOfMonth();
        if (month == 1 || month == 4 || month == 7 || month == 10) {
            if (dayOfMonth == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前时间是否为其周的第一天
     */
    public static boolean getWeek(LocalDateTime time) {
        DayOfWeek dayOfWeek = time.getDayOfWeek();
        int weekDay = dayOfWeek.getValue();
        if (weekDay == DayOfWeek.MONDAY.getValue()) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前时间是否为其月的第一天
     */
    public static boolean getMonth(LocalDateTime time) {
        int dayOfMonth = time.getDayOfMonth();
        return dayOfMonth == 1;
    }

    /**
     * 判断当前时间是否为其年的第一天
     */
    public static boolean getYear(LocalDateTime time) {
        int day = time.getDayOfYear();
        return day == 1;
    }

}
