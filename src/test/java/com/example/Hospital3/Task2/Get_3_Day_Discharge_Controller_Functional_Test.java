package com.example.Hospital3.Task2;

import com.example.Hospital3.Task1.List_Of_Patients_By_Staff_Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import Models.Admission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(Get_3_Day_Discharge_Controller.class)
class Get_3_Day_Discharge_Controller_Functional_Test {

    //mock my dependencies
    @MockBean
    private GetAdmissions getAdmissions;

    @MockBean
    private GetShortStays getShortStays;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet_3_Day_Discharge_Controller() throws Exception {
        Admission admissionTest = new Admission();
        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2023-01-27T17:45:00.000Z"); //short stay ID 78
        admissionTest.setDischargeDate("2020-01-29T17:45:00.000Z");

        Admission admissionTest2 = new Admission();
        admissionTest2.setId(2);
        admissionTest2.setPatientID(79);
        admissionTest2.setAdmissionDate("2023-01-27T17:45:00.000Z");
        admissionTest2.setDischargeDate("2020-12-01T17:45:00.000Z"); //long stay wouldnt expect to see it

        Admission admissionTest3 = new Admission();
        admissionTest3.setId(3);
        admissionTest3.setPatientID(80);
        admissionTest3.setAdmissionDate("2023-11-27T17:45:00.000Z"); //short
        admissionTest3.setDischargeDate("2023-11-28T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[3];
        mockAdmissionsArray[0] = admissionTest;
        mockAdmissionsArray[1] = admissionTest2;
        mockAdmissionsArray[2] = admissionTest3;




        List<Integer> shortStays = Arrays.asList(80, 78);

        // mock the behavior of the getAdmissions and getShortStays dependencies
        when(getAdmissions.getAdmissions()).thenReturn(mockAdmissionsArray);
        when(getShortStays.getShortStays(mockAdmissionsArray)).thenReturn(shortStays);

        // perform the HTTP GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/3daydischarge")
                        .contentType(MediaType.APPLICATION_JSON))
                // assert  statyus code and contents of my simulated response to confirm that it correctly formats and responds
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(80))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(78));
    }




}