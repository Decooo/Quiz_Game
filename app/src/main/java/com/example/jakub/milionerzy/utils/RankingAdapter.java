package com.example.jakub.milionerzy.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jakub.milionerzy.R;
import com.example.jakub.milionerzy.entity.Ranking;

import java.util.ArrayList;

/**
 * Created by jakub on 10.11.2018.
 */

public class RankingAdapter extends ArrayAdapter<Ranking> {

    private final Context context;
    private final ArrayList<Ranking> values;

    public RankingAdapter(Context context, ArrayList<Ranking> values) {
        super(context, 0, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ranking_fragment, parent, false);
        TextView positionText = (TextView) rowView.findViewById(R.id.positionText);
        TextView nameText = (TextView) rowView.findViewById(R.id.nameText);
        TextView resultText = (TextView) rowView.findViewById(R.id.resultText);
        positionText.setText(values.get(position).getPosition());
        nameText.setText(values.get(position).getName());
        resultText.setText(String.valueOf(values.get(position).getResult()));
        if (position % 2 == 0) {
            rowView.setBackgroundResource(R.color.colorRankingDark);
        } else rowView.setBackgroundResource(R.color.colorRankingLight);

        return rowView;
    }
}



