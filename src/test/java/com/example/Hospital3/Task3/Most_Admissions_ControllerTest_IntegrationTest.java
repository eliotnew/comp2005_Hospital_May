package com.example.Hospital3.Task3;

import Models.Admission;
import com.example.Hospital3.Task3.GetAdmissions;
import com.example.Hospital3.Task3.Most_Admissions_Controller;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
class Most_Admissions_ControllerTest_IntegrationTest {

    @Autowired
    private Most_Admissions_Controller mostAdmissionsController;
    

    @MockBean
    private GetAdmissions mockGetAdmissions; // I want to fake the Get response so that a running db is not required for this test.

    @Test
    public void assessString() throws IOException {
        Admission admissionTest = new Admission();
        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2020-11-27T17:45:00.000Z"); //is Friday
        admissionTest.setDischargeDate("2020-11-29T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        when(mockGetAdmissions.getAdmissions()).thenReturn(mockAdmissionsArray);

        String busiestDay = mostAdmissionsController.busiestDay();
        System.out.println(busiestDay);
        boolean isFriday = busiestDay.contains("Friday");
        System.out.println(isFriday);
        assertTrue(isFriday);
    }
}
