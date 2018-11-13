package com.example.jakub.milionerzy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.Answer;
import com.example.jakub.milionerzy.entity.Question;
import com.example.jakub.milionerzy.utils.PostUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jakub on 12.11.2018.
 */

public class GameActivity extends Activity {

    private ArrayList<Question> questionsList = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Bundle bundle = getIntent().getExtras();
        String questionJSON = bundle.getString("questionsJSON");
        getQuestions(questionJSON);
        checkNumberQuestions();

        int round = bundle.getInt("round");
        int result = bundle.getInt("result");

        if (round > questionsList.size()) {
            gameOverDialog(result);
            Toast.makeText(getApplicationContext(), "Nie ma więcej pytań!", Toast.LENGTH_SHORT).show();
        } else {
            TextView resultText = (TextView) findViewById(R.id.textResult);
            String resultString = result + " pkt";
            resultText.setText(resultString);

            TextView roundText = (TextView) findViewById(R.id.textRound);
            String roundString = "Runda " + round;
            roundText.setText(roundString);

            TextView content = (TextView) findViewById(R.id.textContent);
            content.setText(questionsList.get(round - 1).getContent());

            clickAnswer(round, result, questionJSON);
        }
    }

    private void clickAnswer(final int round, final int result, final String questionJSON) {
        final Button answer1 = (Button) findViewById(R.id.btnAnswer1);
        answer1.setText(questionsList.get(round - 1).getAnswerList().get(0).getContent());
        answer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (questionsList.get(round - 1).getAnswerList().get(0).getCorrect()) {
                    answer1.setBackgroundResource(R.color.trueAnswer);
                    try {
                        trueAnswer(round, result, questionJSON);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    answer1.setBackgroundResource(R.color.falseAnswer);
                    falseAnswer(result);
                }
            }
        });

        final Button answer2 = (Button) findViewById(R.id.btnAnswer2);
        answer2.setText(questionsList.get(round - 1).getAnswerList().get(1).getContent());
        answer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (questionsList.get(round - 1).getAnswerList().get(1).getCorrect()) {
                    answer2.setBackgroundResource(R.color.trueAnswer);
                    try {
                        trueAnswer(round, result, questionJSON);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    answer2.setBackgroundResource(R.color.falseAnswer);
                    falseAnswer(result);
                }
            }
        });

        final Button answer3 = (Button) findViewById(R.id.btnAnswer3);
        answer3.setText(questionsList.get(round - 1).getAnswerList().get(2).getContent());
        answer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (questionsList.get(round - 1).getAnswerList().get(2).getCorrect()) {
                    answer3.setBackgroundResource(R.color.trueAnswer);
                    try {
                        trueAnswer(round, result, questionJSON);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    answer3.setBackgroundResource(R.color.falseAnswer);
                    falseAnswer(result);
                }
            }
        });

        final Button answer4 = (Button) findViewById(R.id.btnAnswer4);
        answer4.setText(questionsList.get(round - 1).getAnswerList().get(3).getContent());
        answer4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (questionsList.get(round - 1).getAnswerList().get(3).getCorrect()) {
                    answer4.setBackgroundResource(R.color.trueAnswer);
                    try {
                        trueAnswer(round, result, questionJSON);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    answer4.setBackgroundResource(R.color.falseAnswer);
                    falseAnswer(result);
                }
            }
        });
    }

    private void trueAnswer(int round, int result, String questionJSON) throws InterruptedException {
        result += round * 100;
        round++;

        Intent myIntent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("questionsJSON", questionJSON);
        bundle.putInt("result", result);
        bundle.putInt("round", round);
        myIntent.putExtras(bundle);
        Thread.sleep(500);
        finish();
        startActivity(myIntent);
    }

    private void falseAnswer(int result) {
        if (result == 0) {
            getAlertDialog("Koniec gry", "Spróbuj ponownie!");
        } else {
            gameOverDialog(result);
        }
    }

    private void getQuestions(String response) {
        try {
            if (response.isEmpty()) {
                getAlertDialog("Brak pytań", "Brak pytań dla wybranej kategorii!");
            } else {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Question question = new Question();
                    question.setIdCategory(jsonObject.optInt("idCategory"));
                    question.setContent(jsonObject.optString("content"));

                    ArrayList<Answer> answerList = new ArrayList<>();

                    JSONArray jsonArrayAnswers = jsonObject.getJSONArray("answers");
                    for (int j = 0; j < jsonArrayAnswers.length(); j++) {
                        JSONObject jsonAnswer = jsonArrayAnswers.getJSONObject(j);
                        Answer answer = new Answer();
                        answer.setContent(jsonAnswer.optString("content"));
                        answer.setCorrect(jsonAnswer.optBoolean("correct"));
                        answerList.add(answer);
                    }

                    question.setAnswerList(answerList);
                    questionsList.add(question);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkNumberQuestions() {
        if (questionsList.size() < 5) {
            getAlertDialog("Brak pytań", "Za mało pytań z wybranej kategorii aby rozpoczać quiz");
        }
    }

    private void getAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                GameActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }

    private void gameOverDialog(final int result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Koniec gry! Zdobyłeś " + result + " punktów.");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        input.setPadding(25, 75, 25, 25);
        input.setHint("Wpisz swoja nazwe");
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton("Zapisz wynik", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().isEmpty()) {
                    //input.setError("Nazwa nie może być pusta!");
                    Toast.makeText(getApplicationContext(), "Nazwa nie może być pusta!", Toast.LENGTH_SHORT).show();
                    gameOverDialog(result);
                } else {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("name", input.getText());
                        json.put("result", result);
                        String response = new PostUrl().execute("http://10.0.2.2:8080/ranking/post", String.valueOf(json)).get();
                        if (!response.equalsIgnoreCase("201")) {
                            getAlertDialog("Błąd", "Wystąpił błąd, zapisanie wyniku nie powiodło się.");
                        } else {
                            GameActivity.this.finish();
                            Toast.makeText(getApplicationContext(), "Zapisano wynik!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException | ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
    }
}
