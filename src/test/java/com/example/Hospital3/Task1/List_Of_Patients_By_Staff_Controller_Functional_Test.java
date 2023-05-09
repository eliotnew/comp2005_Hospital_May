package com.example.Hospital3.Task1;

import Models.Allocation;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(List_Of_Patients_By_Staff_Controller.class)
class List_Of_Patients_By_Staff_Controller_Functional_Test {

    //Test that uses mockMVC spring library to check that my controller is capable of sending a resposnebody and converts the output correctly to JSON
    //Also checks HTTP response is correct!  .
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllocations getAllocations;

    @MockBean
    private CreateAdmissionIDList createAdmissionIDList;

    @MockBean
    private GetPatientIDWithAdmissionID getPatientIDWithAdmissionID;

    @MockBean
    private RemoveDuplicates removeDuplicates;

    @Test
    void checkMVC(){
        assertNotNull(mockMvc);
    }

    @Test
    public void testGet_Staffs_Patients() throws Exception {
        // Mock the dependencies
        Allocation[] theAllocations = {new Allocation()};
        when(getAllocations.getAllocations("https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations")).thenReturn(theAllocations);
        when(createAdmissionIDList.createAdmissionIDList(eq(theAllocations), eq(123))).thenReturn(List.of(1, 2));
        when(getPatientIDWithAdmissionID.getPatientIDWithAdmissionID(List.of(1, 2))).thenReturn(List.of(101, 102));
        when(removeDuplicates.RemoveDuplicates(List.of(101, 102))).thenReturn(List.of(101, 102));

        mockMvc.perform(get("/getmypatients/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //cant get this line to work
                .andExpect(jsonPath("$[0]").value(101)) //But these also tell confirm its json though!
                .andExpect(jsonPath("$[1]").value(102));
    }


}