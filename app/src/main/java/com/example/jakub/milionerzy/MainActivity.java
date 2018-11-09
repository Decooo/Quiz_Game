package com.example.jakub.milionerzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jakub.milionerzy.activity.CategoryActivity;
import com.example.jakub.milionerzy.activity.QuestionActivity;
import com.example.jakub.milionerzy.activity.RankingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        Button newGame = (Button) findViewById(R.id.btnNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CategoryActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button addQuestion = (Button) findViewById(R.id.btnAddQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), QuestionActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button ranking = (Button) findViewById(R.id.btnRanking);
        ranking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RankingActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
}



