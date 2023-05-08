package com.example.Hospital3.Task4;

import Models.Allocation;
import com.example.Hospital3.Task1.GetAllocations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Average_Patient_Duration_By_Staff_Controller_IntegrationTest {
    private Average_Patient_Duration_By_Staff_Controller controller;

    private  CalculateStayDurationHours calculateStayDurationHours;
    private  CollectStayDurations collectStayDurations = mock(CollectStayDurations.class);
    private  GetAdmissionIDsFromEmployeeID getAdmissionIDsFromEmployeeID;
    private  ShowAverageHours showAverageHours;
    private GetAllocations mockGetAllocations = mock(GetAllocations.class);




    @BeforeEach
    public void setup() throws IOException {
        Integer mockInputStaffId = 100;
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;
        when(mockGetAllocations.getAllocations(anyString())).thenReturn(testAllocations);

        Double mockHours = 1.00;
        List<Double> mockHoursList = new ArrayList<>();
        mockHoursList.add(mockHours);


        when(collectStayDurations.collectStayDurations(any())).thenReturn(mockHoursList);

        //The non mocked objects are created instances of here!
        calculateStayDurationHours = new CalculateStayDurationHours();
        getAdmissionIDsFromEmployeeID = new GetAdmissionIDsFromEmployeeID();
        showAverageHours = new ShowAverageHours();
        controller = new Average_Patient_Duration_By_Staff_Controller(calculateStayDurationHours, collectStayDurations, getAdmissionIDsFromEmployeeID, showAverageHours, mockGetAllocations);
    }

    @Test
    public void testOutputIntegrated(){
        Double mockHours = 1.00;
        List<Double> mockHoursList = new ArrayList<>();
        mockHoursList.add(mockHours);

        String answer = controller.getAverageDurationByStaff(100);

        //boolean contains1hr = answer.contains("1.00");
        boolean containsid = answer.contains("100");

        assertTrue(containsid);

    }
}