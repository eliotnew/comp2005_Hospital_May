package com.example.Hospital3.Task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicates_Unit_Test {
    private RemoveDuplicates removeDuplicates;

    @BeforeEach
    void setUp() {
        removeDuplicates =new RemoveDuplicates();
    }

    @Test
    public void testRemoveDuplicates(){
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testList.add(1);

        }
        List <Integer> removed = removeDuplicates.RemoveDuplicates(testList);
        assertEquals(1,removed.size());
        assertEquals(1,removed.get(0));
    }
    @Test
    public void testRemoveDuplicates_withNegatives(){
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testList.add(-4);

        }
        List <Integer> removed = removeDuplicates.RemoveDuplicates(testList);
        assertEquals(1,removed.size());
        assertEquals(-4,removed.get(0));
    }
    @Test
    public void testRemoveDuplicates_massiveList(){
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 99999; i++) {
            testList.add(1);

        }
        List <Integer> removed = removeDuplicates.RemoveDuplicates(testList);
        assertEquals(1,removed.size());
        assertEquals(1,removed.get(0));
    }
    @Test
    public void testRemoveDuplicates_noDupesMassive(){
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 99999; i++) {
            testList.add(i);

        }
        List <Integer> removed = removeDuplicates.RemoveDuplicates(testList);
        assertEquals(99999,removed.size());
        assertEquals(0,removed.get(0));
    }

}