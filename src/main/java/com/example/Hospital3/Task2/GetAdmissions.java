package com.example.Hospital3.Task2;

import Models.Admission;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetAdmissions {
    public Admission[] getAdmissions(){
        String API_URL = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";
        try{
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(API_URL);
            request.addHeader("accept", "text/plain");
            CloseableHttpResponse response = httpClient.execute(request);

            // Parse JSON into list of objects using Gson
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Gson gson = new Gson();
            Admission[] admissionArray = gson.fromJson(reader, Admission[].class);

            return admissionArray;

        }catch (Exception e){
            System.out.println("Failed to get admissions.");
            Admission[] failed = new Admission[0];
            return failed;
        }

    }
}
