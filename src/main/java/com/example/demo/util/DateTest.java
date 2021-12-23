package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.ShareModel;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 日期工具类测试
 * @Author elvis
 * @Date 2021/7/20 10:27
 */
public class DateTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse("2020-02-01");
        Date end = new Date();
        Period between = Period.between(dateToLocalDate(start), dateToLocalDate(end));
        System.out.println("两个日期相差" + between.getMonths() + "个月");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        System.out.println(simpleDateFormat.format(date));

        List<ShareModel> list = new ArrayList<>();
        ShareModel model = new ShareModel();
        model.setContractBeginTime("2020-02-13");
        model.setCrossContractBeginTime("2020-03-12");
        list.add(model);
        ShareModel model1 = new ShareModel();
        model1.setContractBeginTime("2020-02-15");
        model1.setCrossContractBeginTime("2020-01-10");
        list.add(model1);
        Collections.sort(list, new Comparator<ShareModel>() {
            @SneakyThrows
            @Override
            public int compare(ShareModel o1, ShareModel o2) {
                Date contractBeginTimeO1 = null;
                Date crossContractBeginTimeO1 = null;
                Date timeO1 = null;
                if (StringUtils.isNotBlank(o1.getContractBeginTime())){
                    contractBeginTimeO1 = format.parse(o1.getContractBeginTime());
                }
                if (StringUtils.isNotBlank(o1.getCrossContractBeginTime())){
                    crossContractBeginTimeO1 = format.parse(o1.getCrossContractBeginTime());
                }
                if (contractBeginTimeO1 != null && crossContractBeginTimeO1 != null){
                    if (contractBeginTimeO1.before(crossContractBeginTimeO1)){
                        timeO1 = contractBeginTimeO1;
                    } else {
                        timeO1 = crossContractBeginTimeO1;
                    }
                } else if (contractBeginTimeO1 != null){
                    timeO1 = contractBeginTimeO1;
                } else {
                    timeO1 = crossContractBeginTimeO1;
                }

                Date contractBeginTimeO2 = null;
                Date crossContractBeginTimeO2 = null;
                Date timeO2 = null;
                if (StringUtils.isNotBlank(o2.getContractBeginTime())){
                    contractBeginTimeO2 = format.parse(o2.getContractBeginTime());
                }
                if (StringUtils.isNotBlank(o2.getCrossContractBeginTime())){
                    crossContractBeginTimeO2 = format.parse(o2.getCrossContractBeginTime());
                }
                if (contractBeginTimeO2 != null && crossContractBeginTimeO2 != null){
                    if (contractBeginTimeO2.before(crossContractBeginTimeO2)){
                        timeO2 = contractBeginTimeO2;
                    } else {
                        timeO2 = crossContractBeginTimeO2;
                    }
                } else if (contractBeginTimeO2 != null){
                    timeO2 = contractBeginTimeO2;
                } else {
                    timeO2 = crossContractBeginTimeO2;
                }

                if (timeO1.before(timeO2)){
                    return -1;
                } else if (timeO1.after(timeO2)){
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(JSON.toJSONString(list));

        Date date1 = format.parse("2019-12-31");
        Date date2 = format.parse("2019-12-01");
        System.out.println(simpleDateFormat.format(date1).equals(simpleDateFormat.format(date2)));

        String str = "2021-09-27T01:17:48.965+0000";
        System.out.println(formatDate(str));
    }

    private static String formatDate(String time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String gpsTime = df2.format(date);
        return gpsTime;
    }


    /**
     * 获取两个日期之间相差几个月，包含起始日期和结束日期当月
     * @param start
     * @param end
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        int result = 0;
        if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
            result = year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            result = year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            result = year * 12 + month;
        } else {
            result = (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
        return result + 1;
    }

    private static LocalDate dateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
