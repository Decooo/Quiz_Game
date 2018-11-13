package com.example.jakub.milionerzy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.Category;
import com.example.jakub.milionerzy.utils.GetUrl;
import com.example.jakub.milionerzy.utils.PostUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jakub on 08.11.2018.
 */

public class QuestionActivity extends Activity {

    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayList<String> categoryName = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        fillCategories();

        Button addQuestion = findViewById(R.id.btnAddQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validationDetails()) {
                    try {
                        String response = new PostUrl().execute("http://10.0.2.2:8080/questions/post", String.valueOf(doJSON())).get();
                        if (!response.equalsIgnoreCase("201")) {
                            getAlertDialogBadPost();
                        } else getAlertDialogSuccessPost();
                    } catch (InterruptedException | ExecutionException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Wprowadzone dane są niepoprawne", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public JSONObject doJSON() throws JSONException {
        EditText content = findViewById(R.id.questionContent);
        EditText trueAnswerText = findViewById(R.id.trueAnswer);
        EditText falseAnswerText1 = findViewById(R.id.falseAnswer1);
        EditText falseAnswerText2 = findViewById(R.id.falseAnswer2);
        EditText falseAnswerText3 = findViewById(R.id.falseAnswer3);
        Spinner spinner = findViewById(R.id.spinnerCategories);
        int index = spinner.getSelectedItemPosition();

        JSONObject json = new JSONObject();
        json.put("idCategory", categories.get(index).getId());
        json.put("content", content.getText());

        JSONArray answers = new JSONArray();
        JSONObject trueAnswer = new JSONObject();
        trueAnswer.put("content", trueAnswerText.getText());
        trueAnswer.put("correct", true);
        answers.put(trueAnswer);

        JSONObject falseAnswer1 = new JSONObject();
        falseAnswer1.put("content", falseAnswerText1.getText());
        falseAnswer1.put("correct", false);
        answers.put(falseAnswer1);

        JSONObject falseAnswer2 = new JSONObject();
        falseAnswer2.put("content", falseAnswerText2.getText());
        falseAnswer2.put("correct", false);
        answers.put(falseAnswer2);

        JSONObject falseAnswer3 = new JSONObject();
        falseAnswer3.put("content", falseAnswerText3.getText());
        falseAnswer3.put("correct", false);
        answers.put(falseAnswer3);

        json.put("answers", answers);
        return json;
    }

    private boolean validationDetails() {
        EditText content = findViewById(R.id.questionContent);
        if (content.getText().toString().isEmpty()) {
            content.setError("Treść pytania nie może być pusta!");
            return false;
        }
        if (content.getText().toString().length() > 500) {
            content.setError("Pytanie może mieć maksymalnie 500 znaków!");
            return false;
        }
        return !validationAnswer();
    }

    private boolean validationAnswer() {
        EditText trueAnswer = findViewById(R.id.trueAnswer);
        EditText falseAnswer1 = findViewById(R.id.falseAnswer1);
        EditText falseAnswer2 = findViewById(R.id.falseAnswer2);
        EditText falseAnswer3 = findViewById(R.id.falseAnswer3);
        if (trueAnswer.getText().toString().isEmpty()) {
            trueAnswer.setError("Treść odpowiedzi nie może być pusta!");
            return true;
        }
        if (trueAnswer.getText().toString().length() > 500) {
            trueAnswer.setError("Odpowiedź może mieć maksymalnie 500 znaków!");
            return true;
        }
        if (falseAnswer1.getText().toString().isEmpty()) {
            falseAnswer1.setError("Treść odpowiedzi nie może być pusta!");
            return true;
        }
        if (falseAnswer1.getText().toString().length() > 500) {
            falseAnswer1.setError("Odpowiedź może mieć maksymalnie 500 znaków!");
            return true;
        }
        if (falseAnswer2.getText().toString().isEmpty()) {
            falseAnswer2.setError("Treść pytania nie może być pusta!");
            return true;
        }
        if (falseAnswer2.getText().toString().length() > 500) {
            falseAnswer2.setError("Pytanie może mieć maksymalnie 500 znaków!");
            return true;
        }
        if (falseAnswer3.getText().toString().isEmpty()) {
            falseAnswer3.setError("Treść pytania nie może być pusta!");
            return true;
        }
        if (falseAnswer3.getText().toString().length() > 500) {
            falseAnswer3.setError("Pytanie może mieć maksymalnie 500 znaków!");
            return true;
        }
        return false;
    }

    private void fillCategories() {
        try {
            String response = new GetUrl().execute("http://10.0.2.2:8080/category/get").get();

            if (response.isEmpty()) {
                getAlertDialog();
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

                Spinner spinner = findViewById(R.id.spinnerCategories);
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
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                QuestionActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }

    public void getAlertDialogBadPost() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Błąd");
        builder.setMessage("Dodawanie pytania nie powiodło się!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                QuestionActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }

    public void getAlertDialogSuccessPost() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sukces!");
        builder.setMessage("Pomyśnie dodawano nowe pytanie!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                QuestionActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }

}
