package org.example.timesimulator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSimulator {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static LocalDateTime localTime = LocalDateTime.now();

    public static void forwardTime() {
        localTime = localTime.plusHours(1);
    }

    public static String getFormattedLocalTime() {
        return localTime.format(TIME_FORMATTER);
    }
}
