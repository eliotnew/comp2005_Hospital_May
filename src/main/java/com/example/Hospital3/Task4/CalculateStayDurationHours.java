package com.example.Hospital3.Task4;

import Models.Admission;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculateStayDurationHours {
    public Double calculateStayDurationHours(Admission admission){
        //formatting the date so that stay duration is calculated
        LocalDateTime admissionDate = LocalDateTime.parse(admission.getAdmissionDate(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime dischargeDate = LocalDateTime.parse(admission.getDischargeDate(), DateTimeFormatter.ISO_DATE_TIME);

        //Using the java.time library to calculate the time span
        Duration duration = Duration.between(admissionDate, dischargeDate);
        //adjust to hours because the default is seconds and nanoseconds which would probably not pass the functional test.
        //because why would anyone need to know that?
        double stayDurationInHours = (double) duration.getSeconds() / (60 * 60);

        return stayDurationInHours;

    }
}
