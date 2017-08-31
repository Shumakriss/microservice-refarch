package org.shumakriss.demo.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chris on 9/4/16.
 */
public class ITSimple {

    String baseUrl = "http://localhost:8080";

    private String makeRequest(String requestUrl) throws Exception {
        String response = "";
        boolean fail = true;
        HttpURLConnection connection = null;
        Integer responseCode = null;

        try{
            URL url = new URL(requestUrl);
            System.out.println("Making request to " + requestUrl + "");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == 200)
                fail = false;

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = br.readLine()) != null)
                response += line;

            responseCode = connection.getResponseCode();
        } finally {
            if(connection != null)
                connection.disconnect();
        }

        if(fail && responseCode != null)
            fail("Failed : HTTP error code = " + responseCode);

        return response;
    }

    @Test
    public void healthTest() throws Exception {
        System.out.println(makeRequest(baseUrl + "/health"));
    }

    @Test
    public void printTest() throws Exception {
        makeRequest(baseUrl + "/print");
    }

    @Test
    public void myEntityTest() throws Exception {
        System.out.println(makeRequest(baseUrl + "/myEntity/1"));
    }
}
