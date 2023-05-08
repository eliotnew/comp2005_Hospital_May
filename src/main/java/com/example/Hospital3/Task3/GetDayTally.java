package com.example.Hospital3.Task3;

import Models.Admission;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GetDayTally {
    public Map<DayOfWeek, Integer> getDayTally(Admission[] admissions) {
        //Creates a hashmap of days of the week and how many tally scores

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();

        for (Admission admission : admissions) {

            LocalDate admission_Date = LocalDate.parse(admission.getAdmissionDate(), DateTimeFormatter.ISO_DATE_TIME);

            //put a score for each day type into the hashmap
            DayOfWeek dayOfWeek = admission_Date.getDayOfWeek();
            dayTally.put(dayOfWeek, dayTally.getOrDefault(dayOfWeek, 0) + 1);
        }
        return dayTally;
    }
}
