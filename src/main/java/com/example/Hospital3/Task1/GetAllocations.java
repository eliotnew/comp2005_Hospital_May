package com.example.Hospital3.Task1;

import Models.Allocation;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class GetAllocations {
    public  Allocation[] getAllocations(String url){


        // apache http client gets the whole table
        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            // Make an array of gson objects from the JSon response using my model "Allocation"
            Gson gson = new Gson();
            Allocation[] allocations = gson.fromJson(responseBody, Allocation[].class);

            return allocations;
        }catch (Exception e){
            System.out.println("error connecting to the api to fetch the allocation table data");
            return new Allocation[0];
        }

    }
}
