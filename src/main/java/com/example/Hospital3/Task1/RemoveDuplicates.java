package com.example.Hospital3.Task1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicates {
    public List<Integer> RemoveDuplicates(List<Integer> patient_ids){
        //I dont really understand how but apparently list to set to list can remove dupes
        Set<Integer> toSet = new LinkedHashSet<>(patient_ids);
        List<Integer> removedDupes = new ArrayList<>(toSet);

        return removedDupes;

    }
}
