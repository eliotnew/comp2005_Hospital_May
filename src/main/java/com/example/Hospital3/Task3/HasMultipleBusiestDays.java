package com.example.Hospital3.Task3;

import java.time.DayOfWeek;
import java.util.Map;

public class HasMultipleBusiestDays {
    public boolean hasMultipleBusiestDays(Map<DayOfWeek, Integer> dayTally) {
        int mostDays = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();

        int numDaysWithHighestCount = 0;
        for (Map.Entry<DayOfWeek, Integer> entry : dayTally.entrySet()) {
            if (entry.getValue() == mostDays) {
                numDaysWithHighestCount++;
            }
        }

        return numDaysWithHighestCount > 1;
    }
}
