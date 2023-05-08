package com.example.Hospital3.Task3;

import Models.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetDayTallyTest_Unit_Test {
    private GetDayTally getDayTally;

    @BeforeEach
    void setUp() {
        this.getDayTally = new GetDayTally();
    }

    @Test
    public void testGetDayTally(){

        String startDate = "2020-11-28T16:45:00"; //was a saturday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        Map<DayOfWeek,Integer> testMap = getDayTally.getDayTally(testAdmissionsArray);


        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
        int tally = testMap.get(dayOfWeek);

        assertNotNull(tally, "tally should not be null");
        assertEquals(1,tally); //asserts that saturday has correctly tallied once


    }

    @Test
    public void testGetDayTally_In100Years(){

        String startDate = "2116-11-28T16:45:00"; // will be a sunday saturday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        Map<DayOfWeek,Integer> testMap = getDayTally.getDayTally(testAdmissionsArray);


        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
        int tally = testMap.get(dayOfWeek);

        assertNotNull(tally, "tally should not be null");
        assertEquals(1, tally, "tally for Monday should be 1");

    }
    @Test
    public void testGetDayTally_100Years_In_Past(){

        String startDate = "1930-11-28T16:45:00"; // friday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        Map<DayOfWeek,Integer> testMap = getDayTally.getDayTally(testAdmissionsArray);


        DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
        int tally = testMap.get(dayOfWeek);

        assertNotNull(tally, "tally should not be null");
        assertEquals(1, tally, "tally for Monday should be 1");

    }



}