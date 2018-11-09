package com.example.jakub.milionerzy.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jakub on 30.10.2018.
 */

public class GetUrl extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... urls) {
        StringBuilder content = new StringBuilder();
        String line;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "*/*");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }else{
                System.err.println("Response code: " + connection.getResponseCode());
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
