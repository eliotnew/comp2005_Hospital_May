package com.example.Hospital3.Task4;

import Models.Admission;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CollectStayDurations {
    public List<Double> collectStayDurations(List<Integer> admissionIDs) throws IOException {
        /*
            THis function takes a list of admissionIDS from the previous function and needs to be called in the constructor
           It does a for loop through the list performing a get by ID to the admissions endpoint to produce a list of stay duration in hours.

         */
        CalculateStayDurationHours calculateStayDurationHours= new CalculateStayDurationHours();

        List<Double> stayDurations = new ArrayList<>(); // A list to store in hours how long the stays are in the admission bodies
        String API_URL_Admissions = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";

        //using a for loop during the apache get by id process
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        for (Integer admissionID : admissionIDs) {
            HttpGet request = new HttpGet(API_URL_Admissions + "/" + admissionID);
            request.addHeader("accept", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);

            // GSON reads the response into its data type which i can extract data  using my model class admission.java
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            JsonParser jsonParser = new JsonParser();
            JsonElement admissionElement = jsonParser.parse(reader).getAsJsonObject();
            Gson gson = new Gson();
            Admission admission = gson.fromJson(admissionElement, Admission.class);

            double stayDurationInHours = calculateStayDurationHours.calculateStayDurationHours(admission);
            stayDurations.add(stayDurationInHours);
        }

        return stayDurations;
    }
}
