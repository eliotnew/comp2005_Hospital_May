package com.example.Hospital3.Task4;

import java.util.List;

public class ShowAverageHours {
    public Double showAverageHours (List<Double> hours){
        double sum = 0.0;
        double meanStay = 0.0;

        if(hours.size()!=0){
            for (double period:hours) {
                sum+=period;
            }
            meanStay += sum/hours.size(); //average stay

        }


        return meanStay;
    }
}
