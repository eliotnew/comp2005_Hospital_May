package com.example.Hospital3.Task4;

import Models.Allocation;
import com.example.Hospital3.Task1.GetAllocations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Average_Patient_Duration_By_Staff_Controller {
    private final CalculateStayDurationHours calculateStayDurationHours;
    private final CollectStayDurations collectStayDurations;
    private final GetAdmissionIDsFromEmployeeID getAdmissionIDsFromEmployeeID;
    private final ShowAverageHours showAverageHours;

    private final GetAllocations getAllocations;

    public Average_Patient_Duration_By_Staff_Controller(CalculateStayDurationHours calculateStayDurationHours,CollectStayDurations collectStayDurations,GetAdmissionIDsFromEmployeeID getAdmissionIDsFromEmployeeID,ShowAverageHours showAverageHours,GetAllocations getAllocations){
        this.calculateStayDurationHours = calculateStayDurationHours;
        this.collectStayDurations = collectStayDurations;
        this.getAdmissionIDsFromEmployeeID = getAdmissionIDsFromEmployeeID;
        this.showAverageHours = showAverageHours;
        this.getAllocations = getAllocations;

    }

    @GetMapping("/duration/{id}")
    public String getAverageDurationByStaff(@PathVariable int id) {
        String API_URL_Allocations = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";
        String answer = "Variable not set";

        try{
            Allocation[] allocations = getAllocations.getAllocations(API_URL_Allocations); //Gets all allocations

            List<Integer> admissionIDs = getAdmissionIDsFromEmployeeID.getAdmissionIDsFromEmployeeID(id,allocations);//Finds all admissions linked to staff ID

            if (admissionIDs.size()!=0){
                List<Double> stayDurations = collectStayDurations.collectStayDurations(admissionIDs); //Collects list of stay durations

                Double meanStayDuration = showAverageHours.showAverageHours(stayDurations); //Calculate average stay in hours

                answer = "The average duration a patient stays for staff ID " + id + " is " + meanStayDuration + " hours.";

            }
            else {return "The Employer ("+id+") hasn't treated any patients yet, please try another ID";}


        }catch (IOException e){
            System.out.println("ERROR Something went wrong");
            answer = "ERROR Something went wrong";

        };
        return answer;
    }
}
