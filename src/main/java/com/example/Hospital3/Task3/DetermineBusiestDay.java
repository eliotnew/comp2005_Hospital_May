package com.example.Hospital3.Task3;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class DetermineBusiestDay {
    public String determineBusiestDay(Map<DayOfWeek, Integer> dayTally) {
        DayOfWeek busiestDay = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return busiestDay.getDisplayName(TextStyle.FULL, Locale.UK);
    }
}
