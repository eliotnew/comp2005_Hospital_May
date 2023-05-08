package com.example.Hospital3.Task3;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class DetermineAllBusyDays {
    public String determineAllBusyDays(Map<DayOfWeek, Integer> dayTally){
        //Should only be called if there are multiple days
        String days = "";
        // checks if it has the same value as the joint modal day value
        int mostDays = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();

        for (Map.Entry<DayOfWeek, Integer> entry : dayTally.entrySet()) {
            if (entry.getValue() == mostDays) {

                days += entry.getKey().getDisplayName(TextStyle.FULL, Locale.UK);
                days += " ";
            }
        }
        return days;
    }
}
