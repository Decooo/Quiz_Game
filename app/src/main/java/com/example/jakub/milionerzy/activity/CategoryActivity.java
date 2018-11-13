package com.example.jakub.milionerzy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.Category;
import com.example.jakub.milionerzy.utils.GetUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jakub on 30.10.2018.
 */

public class CategoryActivity extends Activity {

    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayList<String> categoryName = new ArrayList<String>();
    String responseQuestion = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        fillCategories();

        Button newGame = (Button) findViewById(R.id.btnStart);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GameActivity.class);
                Bundle bundle = new Bundle();
                Spinner spinner = findViewById(R.id.spinnerCategories);
                getQuestions(categories.get(spinner.getSelectedItemPosition()).getId());
                bundle.putString("questionsJSON", responseQuestion);
                bundle.putInt("result", 0);
                bundle.putInt("round", 1);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });

        clickBtnBack();
    }

    private void clickBtnBack() {
        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CategoryActivity.this.finish();
            }
        });
    }

    private void fillCategories() {
        try {
            String response = new GetUrl().execute("http://10.0.2.2:8080/category/getWithQuestions").get();

            if (response.isEmpty()) {
                getAlertDialog("Brak kategorii", "Nie dodatno aktualnie żadnych kategorii");
            } else {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Category category = new Category();
                    category.setName(jsonObject.optString("name"));
                    category.setId(jsonObject.optInt("id"));
                    categories.add(category);
                    categoryName.add(jsonObject.optString("name"));
                }

                Spinner spinner = (Spinner) findViewById(R.id.spinnerCategories);
                spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoryName));
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void getAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CategoryActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }

    private void getQuestions(int idCategory) {
        try {
            String url = "http://10.0.2.2:8080/questions/getQuestion?idCategory=" + idCategory;
            responseQuestion = new GetUrl().execute(url).get();

            if (responseQuestion.isEmpty() || idCategory == 0) {
                getAlertDialog("Brak pytań", "Brak pytań dla wybranej kategorii!");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
