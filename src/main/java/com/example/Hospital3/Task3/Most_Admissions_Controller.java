package com.example.Hospital3.Task3;

import Models.Admission;
import com.example.Hospital3.Task2.GetShortStays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;


@RestController
public class Most_Admissions_Controller {
    private  GetAdmissions getAdmissions;
    private GetDayTally getDayTally;
    private HasMultipleBusiestDays hasMultipleBusiestDays;
    private DetermineAllBusyDays determineAllBusyDays;

    private DetermineBusiestDay determineBusiestDay;

    public Most_Admissions_Controller(GetAdmissions getAdmissions,GetDayTally getDayTally,HasMultipleBusiestDays hasMultipleBusiestDays, DetermineAllBusyDays determineAllBusyDays ,DetermineBusiestDay determineBusiestDay) {
        this.getAdmissions = getAdmissions;
        this.getDayTally = getDayTally;
        this.hasMultipleBusiestDays = hasMultipleBusiestDays;
        this.determineAllBusyDays = determineAllBusyDays;
        this.determineBusiestDay = determineBusiestDay;
    }

    @GetMapping("/busiestday")
    public String busiestDay(){

            //Gets admissions
            Admission[] admissions = getAdmissions.getAdmissions();

            //creates a tally chart in a hashmap
            Map<DayOfWeek, Integer> dayTally = getDayTally.getDayTally(admissions);

            //determines how many days we will return if its one or several answers
            boolean moreThanOne = hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally);


            if(moreThanOne==true){
                String busiestDaysStr = determineAllBusyDays.determineAllBusyDays(dayTally);
                return "There is a joint Tie for busiest Admission days:"+busiestDaysStr;
            }
            else{
                String busiestDayStr = determineBusiestDay.determineBusiestDay(dayTally);
                return "Busiest day of the week is "+busiestDayStr;
            }


    }
}
