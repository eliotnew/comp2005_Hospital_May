package com.example.Hospital3.Task3;

import Models.Admission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;


@RestController
public class Most_Admissions_Controller {
    GetAdmissions getAdmissions = new GetAdmissions();
    GetDayTally getDayTally = new GetDayTally();
    HasMultipleBusiestDays hasMultipleBusiestDays = new HasMultipleBusiestDays();
    DetermineAllBusyDays determineAllBusyDays = new DetermineAllBusyDays();

    DetermineBusiestDay determineBusiestDay = new DetermineBusiestDay();

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
