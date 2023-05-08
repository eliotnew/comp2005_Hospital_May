package com.example.Hospital3.Task1;

import Models.Allocation;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class List_Of_Patients_By_Staff_Controller_IntegrationTest {


    private GetAllocations mockGetAllocations = mock(GetAllocations.class);
    private CreateAdmissionIDList realCreateAdmissionIDList = new CreateAdmissionIDList();
    private GetPatientIDWithAdmissionID mockGetStaffsPatients = mock(GetPatientIDWithAdmissionID.class);
    private RemoveDuplicates realRemove = new RemoveDuplicates();
    List_Of_Patients_By_Staff_Controller realController;
    String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";



    @Before
    public void setup(){

       realController = new List_Of_Patients_By_Staff_Controller(mockGetAllocations,realCreateAdmissionIDList,mockGetStaffsPatients,realRemove);


        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(40);
        allocation1.setStartTime("2020-11-28T16:45:00.000Z");
        allocation1.setEndTime("2020-11-28T17:45:00.000Z");

        testAllocations[0] = allocation1;

        when(mockGetAllocations.getAllocations(url)).thenReturn(testAllocations); //does not require a running database and api


        List<Integer> mockPatientIDList = new ArrayList<>();
        mockPatientIDList.add(72);


        when(mockGetStaffsPatients.getPatientIDWithAdmissionID(any())).thenReturn(mockPatientIDList); //saves doing an api get

    }

    @Test
    void IntegrateTestThatListCorrectlyReturned(){
        List<Integer> result = realController.Get_Staffs_Patients(40); //takes list of admission ids
        assertEquals(1,result.size());
    }



}