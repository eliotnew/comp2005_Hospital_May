package com.example.Hospital3.Task4;

import Models.Allocation;

import java.util.ArrayList;
import java.util.List;

public class GetAdmissionIDsFromEmployeeID {
    public List<Integer> getAdmissionIDsFromEmployeeID(int employeeID, Allocation[] allocations) {
        List<Integer> admissionIDs = new ArrayList<>();
        for (Allocation allocation : allocations) {
            if (allocation.getEmployeeID() == employeeID) {
                admissionIDs.add(allocation.getAdmissionID());
            }
        }
        return admissionIDs;
    }
}
