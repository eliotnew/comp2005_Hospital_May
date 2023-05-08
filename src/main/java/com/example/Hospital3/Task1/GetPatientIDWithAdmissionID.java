package com.example.Hospital3.Task1;

import Models.Admission;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GetPatientIDWithAdmissionID {
    public List<Integer> getPatientIDWithAdmissionID(List<Integer> admission_ids){

        //Using the list of admissionIds we can get the patientsId from the admission

        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions/";
        HttpClient client = HttpClientBuilder.create().build();

        List<Integer> patientIDs = new ArrayList<>();

        for (Integer admission_id :admission_ids) {
            //apache get by ID
            try {
                HttpGet request = new HttpGet(url + admission_id);
                HttpResponse response = client.execute(request);
                String responseBody = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();
                Admission admission = gson.fromJson(responseBody, Admission.class);
                //using the getter from my admission class i collect the patient ID
                int patientID = admission.getPatientID();
                patientIDs.add(patientID);

            }catch (Exception e){
                patientIDs.add(-999);
            }

        }
        return patientIDs;
    }


}
