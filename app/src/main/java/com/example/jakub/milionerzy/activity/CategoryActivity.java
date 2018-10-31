package com.example.jakub.milionerzy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.CategoryEntity;
import com.example.jakub.milionerzy.utils.GetUrlContentTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jakub on 30.10.2018.
 */

public class CategoryActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        ArrayList<CategoryEntity> categories = new ArrayList<CategoryEntity>();
        ArrayList<String> categoryName = new ArrayList<String>();
        try {
            String response = new GetUrlContentTask().execute("http://10.0.2.2:8080/category/get").get();

            if (response.isEmpty()) {
                getAlertDialog();
            } else {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setName(jsonObject.optString("name"));
                    categoryEntity.setId(jsonObject.optInt("id"));
                    categories.add(categoryEntity);
                    categoryName.add(jsonObject.optString("name"));
                }

                Spinner spinner = (Spinner) findViewById(R.id.spinnerCategories);
                spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoryName));
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void getAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Brak kategorii");
        builder.setMessage("Nie dodatno aktualnie żadnych kategorii");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CategoryActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }

}
