package com.example.Hospital3.Task1;

import Models.Allocation;
import com.example.Hospital3.Task2.GetAdmissions;
import com.example.Hospital3.Task2.GetShortStays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class List_Of_Patients_By_Staff_Controller {

    private final GetAllocations getAllocations;
    private final CreateAdmissionIDList createAdmissionIDList;
    private final GetPatientIDWithAdmissionID getPatientIDWithAdmissionID;
    private final RemoveDuplicates removeDuplicates;

    public List_Of_Patients_By_Staff_Controller(GetAllocations getAllocations, CreateAdmissionIDList createAdmissionIDList,GetPatientIDWithAdmissionID getPatientIDWithAdmissionID,RemoveDuplicates removeDuplicates) {
        this.getAllocations = getAllocations;
        this.createAdmissionIDList = createAdmissionIDList;
        this.getPatientIDWithAdmissionID = getPatientIDWithAdmissionID;
        this.removeDuplicates=removeDuplicates;
    }


    @GetMapping("/getmypatients/{id}")
    public List<Integer> Get_Staffs_Patients(@PathVariable int id) {
        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";
        try {
            //Creates an array of models from a GET
            Allocation[] theAllocations = getAllocations.getAllocations(url);

            //Creates a List of Admission IDs
            List<Integer> patientsAdmissions = createAdmissionIDList.createAdmissionIDList(theAllocations, id);

            //Creates a List of Patients
            List<Integer> patientIDs = getPatientIDWithAdmissionID.getPatientIDWithAdmissionID(patientsAdmissions);

            //Ensures that there are no duplicates in the list as there is a many to one relationship between admissions and patient.
            List<Integer> yourPatients = removeDuplicates.RemoveDuplicates(patientIDs);

            return yourPatients;
        }
        catch (Exception e){
            System.out.println("Went wrong!");
            List<Integer> failList = new ArrayList<>();
            failList.add(-999);
            return failList;
        }
    }

}
