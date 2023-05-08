package com.example.Hospital3.Task2;

import Models.Admission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class Get_3_Day_Discharge_Controller {

    private final GetAdmissions getAdmissions;
    private final GetShortStays getShortStays;

    public Get_3_Day_Discharge_Controller(GetAdmissions getAdmissions, GetShortStays getShortStays) {
        this.getAdmissions = getAdmissions;
        this.getShortStays = getShortStays;
    }

    @GetMapping("/3daydischarge")
    public List<Integer> get_3_Day_Discharge_Controller() {

        try {
            Admission[] admissions = getAdmissions.getAdmissions(); //Gets all admissions
            System.out.println("Admissions: " + Arrays.toString(admissions));

            List<Integer> patientsIDs = getShortStays.getShortStays(admissions); // Sorts through them retreiving IDs of patients with a short stay
            System.out.println("Short Stays: " + patientsIDs);

            return patientsIDs;

        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            int size = 1;
            List<Integer> failList = new ArrayList<>(Collections.nCopies(size, -1));

            return failList;
        }
    }
}


