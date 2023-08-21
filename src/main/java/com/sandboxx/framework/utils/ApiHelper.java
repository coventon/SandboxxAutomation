package com.sandboxx.framework.utils;

import com.sandboxx.dataManagement.ConfigProcessor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiHelper {

    public static void deleteAccount(String phoneNumber, String emailAddress){

        String apiUrl = "https://api-stage.sandboxx.us/rest/test/automation/delete/leads";
        String apiEmail = ConfigProcessor.getSandboxxApiEmail();
        String apiPwd = ConfigProcessor.getSandboxxApiPwd();

        // Build the API endpoint with query params
        String endpoint = apiUrl + "?phoneNumber=" + phoneNumber + "&emailAddress=" + emailAddress + "&u="+apiEmail+"&t=" + apiPwd;

        // Create HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // Create Http GET request object
        HttpGet httpGet = new HttpGet(endpoint);

        try{
            org.apache.http.HttpResponse response = httpClient.execute(httpGet);
            // get response status code
            int statusCode = response.getStatusLine().getStatusCode();

            // Print response code
            System.out.println("Response Status Code: " + statusCode);

            // Get response body
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response Body: " + responseBody);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void deleteAccount2(){

        String phoneNumber ="4057704109";
        String emailAddress = "jdoe1234@mail.com";

        String apiUrl = "https://api-stage.sandboxx.us/rest/test/automation/delete/leads";
        String apiEmail = "team@sandboxx.us";
        String apiPwd = "SandboxxTest2001!";

        // Build the API endpoint with query params
        String endpoint = apiUrl + "?phoneNumber=" + phoneNumber + "&emailAddress=" + emailAddress + "&u="+apiEmail+"&t=" + apiPwd;
        //String endpoint = "https://api-stage.sandboxx.us/rest/test/automation/delete/leads?phoneNumber=4057704109&emailAddress=jdoe1234@mail.com&u=team@sandboxx.us&t=SandboxxTest2001!";
        // Create HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // Create Http GET request object
        HttpGet httpGet = new HttpGet(endpoint);

        try{
            org.apache.http.HttpResponse response = httpClient.execute(httpGet);
            // get response status code
            int statusCode = response.getStatusLine().getStatusCode();

            // Print response code
            System.out.println("Response Status Code: " + statusCode);

            // Get response body
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response Body: " + responseBody);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void createLeadEntity(String phoneNumber, String emailAddress){

        //api-stage.sandboxx.us/rest/test/automation/create/leads?phoneNumber=[PHONE NUMBER]&emailAddress=[EMAIL ADDRESS]t&u=team@sandboxx.us&t=SandboxxTest2001!
        String apiUrl = "https://api-stage.sandboxx.us/rest/test/automation/create/leads";
        String apiEmail = ConfigProcessor.getSandboxxApiEmail();
        String apiPwd = ConfigProcessor.getSandboxxApiPwd();

        // Build the API endpoint with query params
        String endpoint = apiUrl + "?phoneNumber=" + phoneNumber + "&emailAddress=" + emailAddress + "&u="+apiEmail+"&t=" + apiPwd;

        // Create HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // Create Http GET request object
        HttpGet httpGet = new HttpGet(endpoint);

        try{
            org.apache.http.HttpResponse response = httpClient.execute(httpGet);
            // get response status code
            int statusCode = response.getStatusLine().getStatusCode();

            // Print response code
            System.out.println("Response Status Code: " + statusCode);

            // Get response body
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response Body: " + responseBody);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
