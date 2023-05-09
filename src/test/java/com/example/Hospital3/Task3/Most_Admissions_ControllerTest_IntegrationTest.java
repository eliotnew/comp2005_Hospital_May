package com.example.Hospital3.Task3;

import Models.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class Most_Admissions_ControllerTest_IntegrationTest {
    private Most_Admissions_Controller mostAdmissionsController;
    private GetDayTally getDayTally;
    private HasMultipleBusiestDays hasMultipleBusiestDays;
    private DetermineAllBusyDays determineAllBusyDays;
    private DetermineBusiestDay determineBusiestDay;
    private GetAdmissions mockGetAdmissions; // I want to fake the Get response so that a running db is not required for this test.

    @BeforeEach
    public void setup(){
        Admission admissionTest = new Admission();
        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2020-11-27T17:45:00.000Z"); //is Friday
        admissionTest.setDischargeDate("2020-11-29T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;
        mockGetAdmissions = mock(GetAdmissions.class);
        when(mockGetAdmissions.getAdmissions()).thenReturn(mockAdmissionsArray);


        getDayTally = new GetDayTally();
        hasMultipleBusiestDays = new HasMultipleBusiestDays();
        determineAllBusyDays = new DetermineAllBusyDays();
        determineBusiestDay = new DetermineBusiestDay();


        mostAdmissionsController = new Most_Admissions_Controller(mockGetAdmissions,getDayTally,hasMultipleBusiestDays,determineAllBusyDays,determineBusiestDay);
    }
    @Test
    public void assessString() throws IOException {


        String busiestDay = mostAdmissionsController.busiestDay();
        System.out.println(busiestDay);
        boolean isFriday = busiestDay.contains("Friday");
        System.out.println(isFriday);
        assertTrue(isFriday);
    }
}
