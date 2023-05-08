package com.example.Hospital3.Task4;

import Models.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateStayDurationHours_Unit_Test {
    CalculateStayDurationHours calculateStayDurationHours;
    @BeforeEach
    public void setup(){
        this.calculateStayDurationHours = new CalculateStayDurationHours();
    }
    @Test
    void testCalculateStayDurationHours(){

        String startDate = "2020-11-28T16:45:00";
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        double result = calculateStayDurationHours.calculateStayDurationHours(admissionTest);

        assertEquals(1.00,result);

    }

    @Test
    void testCalculateStayDurationHours_Negative(){

        String startDate = "2020-11-28T17:45:00";
        String finishDate = "2020-11-28T16:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        double result = calculateStayDurationHours.calculateStayDurationHours(admissionTest);

        assertEquals(-1.00,result);

    }

    @Test
    void testCalculateStayDurationHours_Massive(){

        String startDate = "2020-11-28T16:45:00";
        String finishDate = "2021-11-28T16:45:00"; //there are 8760 hours in a year

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        double result = calculateStayDurationHours.calculateStayDurationHours(admissionTest);

        assertEquals(8760.00,result); //Can calculate  a year stay

    }

}