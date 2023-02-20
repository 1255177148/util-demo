package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DateUtilDemo {


    @Test
    public void dateTest() throws ParseException {
        Date date = DateUtils.addMonths(new Date(), 13);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));
        String str = "2022-12-15 15:30";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(format.format(dateFormat.parse(str)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = simpleDateFormat.parse("2023-02-16");
        Date d2 = simpleDateFormat.parse("2023-02-16");
        System.out.println(d1.before(d2));
        System.out.println(daysAgoOrAfter(simpleDateFormat.parse("2023-02-28"), -0 + 1));
    }

    /**
     * 两个时间相差的秒数
     */
    @Test
    public void name() throws ParseException {
        String startTime = "2021-10-18 14:05:00";
        String endTime = "2021-10-18 14:07:02";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long eTime = df.parse(endTime).getTime();
        long sTime = df.parse(startTime).getTime();
        long diff = (eTime - sTime) / 1000;
        System.out.println(diff);

        BigDecimal a = new BigDecimal("6");
        BigDecimal bigDecimal = new BigDecimal("-1");
        System.out.println(a.add(bigDecimal).stripTrailingZeros().toPlainString());
    }

    @Test
    public void test() throws ParseException {
        String time = "2023-02";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse(time));
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(day);
    }

    /**
     * 获取指定日期的前n天或者后n天的日期
     * @param day 指定日期
     * @param amount n，前n天就传负数，后n天就传正数
     * @return
     */
    public static String daysAgoOrAfter(Date day, int amount){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar mon = Calendar.getInstance();
        mon.setTime(day);
        mon.add(Calendar.DATE,amount);
        return format.format(mon.getTime());
    }
}
