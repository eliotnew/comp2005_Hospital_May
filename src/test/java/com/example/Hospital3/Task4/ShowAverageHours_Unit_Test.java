package com.example.Hospital3.Task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowAverageHours_Unit_Test {
    ShowAverageHours showAverageHours;

    @BeforeEach
    public void setup(){
        this.showAverageHours = new ShowAverageHours();
    }
    @Test
    void testShowAverageHours_WithMassiveList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 99999; i++) {
            massiveList.add(5.5);
        }

        Double expected = 5.5;
        Double Actual = showAverageHours.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }

    @Test
    void testShowAverageHours_WithZeroesList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            massiveList.add(0.0);
        }

        Double expected = 0.0;
        Double Actual = showAverageHours.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }

    @Test
    void testShowAverageHours_WithZeroesMixedInList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            massiveList.add((double) i);
        }

        Double expected = 2.5;
        Double Actual = showAverageHours.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }

    @Test
    void testShowAverageHours_WithLargeNumbersList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            massiveList.add(691236123.23);
        }

        Double expected = 691236123.23;
        Double Actual = showAverageHours.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }



}