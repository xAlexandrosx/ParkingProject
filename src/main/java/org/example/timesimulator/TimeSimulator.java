package org.example.timesimulator;

import java.time.LocalDateTime;

public class TimeSimulator {
    public static LocalDateTime localTime = LocalDateTime.now();

    public static void forwardTime() {
        localTime = localTime.plusHours(1);
    }
}
