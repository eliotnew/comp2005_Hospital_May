package com.example.Hospital3.Task4;

import static org.junit.jupiter.api.Assertions.*;
import Models.Allocation;
import com.example.Hospital3.Task1.GetAllocations;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = Average_Patient_Duration_By_Staff_Controller.class)
class Average_Patient_Duration_By_Staff_Controller_Functional_Test {

    //Mocked dependancies for the controller are made as beans here so i can define their behaviour
    //This mocks the internals of the controller but the aim is to check how it presents its data over the endpoint
    //I am looking that it returns a correct response of a JSOn and the right response code
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllocations getAllocations;

    @MockBean
    private CalculateStayDurationHours calculateStayDurationHours;

    @MockBean
    private CollectStayDurations collectStayDurations;

    @MockBean
    private GetAdmissionIDsFromEmployeeID getAdmissionIDsFromEmployeeID;

    @MockBean
    private ShowAverageHours showAverageHours;

    @InjectMocks
    private Average_Patient_Duration_By_Staff_Controller controller;

    @Test
    public void testGetAverageDurationByStaff_WhenMatches() throws Exception {
        Allocation[] allocations = new Allocation[]{new Allocation()};
        when(getAllocations.getAllocations(anyString())).thenReturn(allocations);

        when(getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(anyInt(), any())).thenReturn(Arrays.asList(1, 2, 3));

        when(collectStayDurations.collectStayDurations(any())).thenReturn(Arrays.asList(1.0, 2.0, 3.0));

        when(showAverageHours.showAverageHours(any())).thenReturn(2.0);
        //Populated list returns an average of 2 which means that the response string is properly made with content.

        mockMvc.perform(MockMvcRequestBuilders.get("/duration/{id}", 123)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The average duration a patient stays for staff ID 123 is 2.0 hours."));
    }

    @Test
    public void testGetAverageDurationByStaff_NoMatchCase() throws Exception {
        Allocation[] allocations = new Allocation[]{new Allocation()};
        when(getAllocations.getAllocations(anyString())).thenReturn(allocations);

        when(getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(anyInt(), any())).thenReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders.get("/duration/{id}", 33291)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The Employer (33291) hasn't treated any patients yet, please try another ID"));
    }

}