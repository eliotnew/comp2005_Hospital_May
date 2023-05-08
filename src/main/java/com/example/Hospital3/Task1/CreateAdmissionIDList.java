package com.example.Hospital3.Task1;

import Models.Allocation;

import java.util.ArrayList;
import java.util.List;

public class CreateAdmissionIDList {
    public List<Integer> createAdmissionIDList(Allocation[] data , int id){

        // list to store the patients by their admissionIDs
        List<Integer> admissionIDs = new ArrayList<>();

        // for Loop through each object in the array staff member given staff member in the object
        for (Allocation allocation : data) {
            if (allocation.getEmployeeID() == id) {
                // If the same, adds patient to list.
                admissionIDs.add(allocation.getAdmissionID());
            }
        }

        // Return the list of patients that have the same staff member given.
        return admissionIDs;

    }
}
