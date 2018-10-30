package com.example.jakub.milionerzy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jakub.milionerzy.MainActivity;
import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.CategoryEntity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jakub on 30.10.2018.
 */

public class CategoryActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        String readFeed = readFeed();

        ArrayList<CategoryEntity> categories = new ArrayList<CategoryEntity>();
        ArrayList<String> categoryName = new ArrayList<String>();

        try {
            JSONObject json = new JSONObject(readFeed);
            JSONArray jsonArray = new JSONArray(json.optString("categories"));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setName(jsonObject.optString("name"));
                categoryEntity.setId(jsonObject.optInt("id"));
                categories.add(categoryEntity);
                categoryName.add(jsonObject.optString("name"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategories);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoryName));

    }

    public String readFeed() {
        StringBuilder builder = new StringBuilder();
//        HttpClient client = new DefaultHttpClient();
//
//        HttpGet httpGet = new HttpGet("http://localhost:8080/category/get");
//        try {
//            HttpResponse response = client.execute(httpGet);
//            StatusLine statusLine = response.getStatusLine();
//            int statusCode = statusLine.getStatusCode();
//            if (statusCode == 200) {
//                HttpEntity entity = response.getEntity();
//                InputStream content = entity.getContent();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                }
//            } else {
//                Log.e(CategoryActivity.class.toString(), "Failed to download lists categories");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return builder.toString();

    }

}
