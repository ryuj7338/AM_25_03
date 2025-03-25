package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    /** 날짜 시각 생성**/
    public static String getNowStr () {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatedNow;
    }
}
