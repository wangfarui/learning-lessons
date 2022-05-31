package com.wfr.learning.in.jdk.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/5/31
 */
public class WorkDateUtils {

    private static final LocalTime GET_OFF_WORK_TIME = LocalTime.of(19, 0, 0);

    public static void main(String[] args) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("现在是北京时间: " + nowDateTime);

        getOffWork(nowDateTime);
    }

    public static void getOffWork(LocalDateTime nowDateTime) {
        LocalTime nowTime = nowDateTime.toLocalTime();
        LocalTime getOffWorkTime = GET_OFF_WORK_TIME;
        if (nowTime.isAfter(getOffWorkTime)) {
            System.out.println("已经下班了！还在卷?");
        } else {
            LocalTime remainingTime = getOffWorkTime.minusSeconds(nowTime.getSecond())
                    .minusMinutes(nowTime.getMinute())
                    .minusHours(nowTime.getHour());
            System.out.println("距离" + GET_OFF_WORK_TIME + "下班还剩: "
                    + remainingTime.getHour() + "小时"
                    + remainingTime.getMinute() + "分钟"
                    + remainingTime.getSecond() + "秒");
        }
    }
}
