package com.example.Hospital3.Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HasMultipleBusiestDays_Unit_Test {
    HasMultipleBusiestDays hasMultipleBusiestDays;

    @BeforeEach
    public void setup(){
        hasMultipleBusiestDays = new HasMultipleBusiestDays();
    }

    @Test
    public void testHasMultipleBusiestDays(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertFalse(test); //should just be one busiest day

    }

    @Test
    public void testHasMultipleBusiestDays_True(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertTrue(test); //should just be one busiest day

    }

    @Test
    public void testHasMultipleBusiestDays_minus(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, -1);
        dayTally.put(DayOfWeek.FRIDAY, 1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertFalse(test); //should just be one busiest day

    }

    @Test
    public void testHasMultipleBusiestDays_two_minus(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, -1);
        dayTally.put(DayOfWeek.FRIDAY, -1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertTrue(test); //no entries values means no patients

    }

    @Test
    public void testHasMultipleBusiestDays_moreThanTwo(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.TUESDAY, 1);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertTrue(test); //no entries values means no patients

    }
    @Test
    public void testHasMultipleBusiestDays_massiveNumber(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 100000);
        dayTally.put(DayOfWeek.TUESDAY, 1);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertFalse(test);

    }

    @Test
    public void testHasMultipleBusiestDays_massiveNumbersTrue(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 100000);
        dayTally.put(DayOfWeek.TUESDAY, 100000);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        Boolean test = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);

        assertTrue(test);
    }



}