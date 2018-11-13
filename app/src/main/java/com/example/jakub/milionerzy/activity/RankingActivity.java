package com.example.jakub.milionerzy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.Ranking;
import com.example.jakub.milionerzy.utils.GetUrl;
import com.example.jakub.milionerzy.utils.RankingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jakub on 09.11.2018.
 */

public class RankingActivity extends Activity {

    ArrayList<Ranking> rankings = new ArrayList<Ranking>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        try {
            String response = new GetUrl().execute("http://10.0.2.2:8080/ranking/get").get();

            if (response.isEmpty()) {
                getAlertDialog();
            } else {
                doJsonResponse(response);

                ListView listView = (ListView) findViewById(R.id.listViewRanking);
                listView.setAdapter(new RankingAdapter(this,rankings));
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RankingActivity.this.finish();
            }
        });

    }

    private void doJsonResponse(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Ranking ranking = new Ranking();
            ranking.setName(jsonObject.optString("name"));
            ranking.setResult(jsonObject.optInt("result"));
            ranking.setPosition(String.valueOf(i+1));

            rankings.add(ranking);
        }
    }

    public void getAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Brak wyników");
        builder.setMessage("Aktualnie nie ma żadnych wyników");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                RankingActivity.this.finish();
            }
        }).show();
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(500, 600);
    }


}
