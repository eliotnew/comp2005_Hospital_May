package com.example.Hospital3.Task2;

import Models.Admission;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetShortStays {
    public List<Integer> getShortStays(Admission[] admissions){
        //returns list of patient IDs who stayed less than 3 days

        List<Integer> patientIDs  = new ArrayList<>();
        List<Admission> admissionList = Arrays.asList(admissions);

        //  Filter each admissions where the stay is less than or equal to 3 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        for (Admission admission : admissionList) {

            //Gets the formatted dates per each admission entry and calculates the duration of stay in days

            LocalDateTime admissionDate = LocalDateTime.parse(admission.getAdmissionDate(), formatter);
            LocalDateTime dischargeDate = LocalDateTime.parse(admission.getDischargeDate(), formatter);
            Duration duration = Duration.between(admissionDate, dischargeDate);
            long hoursDuration = duration.toHours();


            Integer threeDays = 72;
            Integer zero = 0;
            long zeroL= zero;
            long threeDaysLong = threeDays;


            if (hoursDuration <= threeDaysLong && hoursDuration > zeroL) {
                patientIDs.add(admission.getPatientID());
            }

        }
        return patientIDs;
    }
}
