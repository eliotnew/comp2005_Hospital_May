package com.example.Hospital3.Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DetermineAllBusyDays_Unit_Test {
    DetermineAllBusyDays determineAllBusyDays;

    @BeforeEach
    public void setup(){
        determineAllBusyDays = new DetermineAllBusyDays();
    }

    @Test
    public void testDetermineAllBusiestDays (){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);

        String test = determineAllBusyDays.determineAllBusyDays(dayTally);
        System.out.println(test);

        assertTrue(test.equals("Monday Thursday ") || test.equals("Thursday Monday "));

    }

    @Test
    public void testDetermineAllBusiestDays_All_Days (){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.TUESDAY, 1);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        String test = determineAllBusyDays.determineAllBusyDays(dayTally);
        System.out.println(test);

        assertTrue(test.contains("Monday "));
        assertTrue(test.contains("Tuesday "));
        assertTrue(test.contains("Wednesday "));
        assertTrue(test.contains("Thursday "));
        assertTrue(test.contains("Friday "));
        assertTrue(test.contains("Saturday "));
        assertTrue(test.contains("Sunday "));

    }

}