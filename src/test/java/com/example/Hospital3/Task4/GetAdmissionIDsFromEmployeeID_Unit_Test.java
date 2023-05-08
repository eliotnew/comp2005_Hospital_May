package com.example.Hospital3.Task4;

import Models.Allocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAdmissionIDsFromEmployeeID_Unit_Test {

    GetAdmissionIDsFromEmployeeID getAdmissionIDsFromEmployeeID = new GetAdmissionIDsFromEmployeeID();

    @BeforeEach
    public void setup(){
        this.getAdmissionIDsFromEmployeeID = new GetAdmissionIDsFromEmployeeID();
    }
    @Test
    void testGetAdmissionIdsFromEmployeeID_With1(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(100,testAllocations);

        assertEquals(1,testList.size());
        assertEquals(121,testList.get(0));

    }

    @Test
    void testGetAdmissionIdsFromEmployeeID_WithNegative(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(-121);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(100,testAllocations);

        assertEquals(1,testList.size());
        assertEquals(-121,testList.get(0));

    }

    @Test
    void testGetAdmissionIdsFromEmployeeID_WithMassiveValue(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(123456789);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(100,testAllocations);

        assertEquals(1,testList.size());
        assertEquals(123456789,testList.get(0));

    }

    @Test
    void testGetAdmissionIdsFromEmployeeID_WithNoMatch(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(123456789);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(50,testAllocations);

        assertEquals(0,testList.size());

    }

}