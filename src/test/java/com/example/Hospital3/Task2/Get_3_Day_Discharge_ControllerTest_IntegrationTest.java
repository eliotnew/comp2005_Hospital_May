package com.example.Hospital3.Task2;

import Models.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Get_3_Day_Discharge_ControllerTest_IntegrationTest {
    private Get_3_Day_Discharge_Controller get_3_Day_Discharge_Controller;
    private GetAdmissions mockGetAdmissions;
    private GetShortStays realGetShortStays;

    @BeforeEach
    public void setup(){
        mockGetAdmissions = mock(GetAdmissions.class);
        realGetShortStays = new GetShortStays();

        Admission admissionTest = new Admission();
        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2020-11-27T17:45:00.000Z");
        admissionTest.setDischargeDate("2020-11-29T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        when(mockGetAdmissions.getAdmissions()).thenReturn(mockAdmissionsArray);

        get_3_Day_Discharge_Controller = new Get_3_Day_Discharge_Controller(mockGetAdmissions, realGetShortStays);
    }

    @Test
    public void shouldReturnListOfShortStayPatients(){
        List<Integer> result = get_3_Day_Discharge_Controller.get_3_Day_Discharge_Controller();

        // Check that the result list contains the patient ID of the admission with a short stay
        assertTrue(result.contains(78));


    }
}
