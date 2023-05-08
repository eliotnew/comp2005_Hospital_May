package com.example.Hospital3.Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DetermineBusiestDay_Unit_Test {
    DetermineBusiestDay determineBusiestDay;
    @BeforeEach
    public void setup(){
        determineBusiestDay=new DetermineBusiestDay();
    }

    @Test
    public void testDetermineBusiestDay(){
        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);

        String test = determineBusiestDay.determineBusiestDay(dayTally);

        System.out.println(test);
        assertEquals( "Monday",test);
    }
    @Test
    public void testDetermineBusiestDay_Massive (){
        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 999999999);

        String test = determineBusiestDay.determineBusiestDay(dayTally);

        System.out.println(test);
        assertEquals( "Monday",test);

    }
    @Test
    public void testDetermineBusiestDay_zero (){
        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 0);
        dayTally.put(DayOfWeek.THURSDAY, 1);

        String test = determineBusiestDay.determineBusiestDay(dayTally);

        System.out.println(test);
        assertEquals( "Thursday",test);
    }

}