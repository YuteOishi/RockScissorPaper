package com.example.sample;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.CYAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class History extends AppCompatActivity {

    private LinearLayout layout;
    int winNum;
    int loseNum;
    int textNum;
    int i;
    int j;
    int fightNum;
    String[][] history = new String[1000][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        layout = findViewById(R.id.historyLinear);
        Intent intent = getIntent();
        winNum = intent.getIntExtra("WIN",0);
        loseNum = intent.getIntExtra("LOSE",0);
        fightNum = intent.getIntExtra("FIGHT", 0);
        for (int i = 0; i < fightNum; i++) {
            for (int j = 0; j < 5; j++) {
                history[i][j] = intent.getStringExtra(i + " " + j);
            }
            j = 0;
        }
        i = 0;


        for (int i=0; i<fightNum; i++){
        for (int j = 0; j < 5; j++) {
            View hisview = getLayoutInflater().inflate(R.layout.his1, null);
            layout.addView(hisview);
            textNum++;
            TextView text = (TextView) findViewById(R.id.text);
            switch (j) {
                case 0:
                    text.setTextSize(40);
                    text.setText("第" + history[i][0] + "戦目");
                    textColor(text, i);
                    break;
                case 1:
                    text.setTextSize(25);
                    text.setText(history[i][1]);
                    textColor(text, i);
                    break;
                case 2:
                    text.setText(history[i][2]);
                    textColor(text, i);
                    break;
                case 3:
                    text.setText(history[i][3]);
                    textColor(text, i);
                    break;
                case 4:
                    text.setText(history[i][4]);
                    textColor(text, i);
                    break;
            }
            ((TextView) findViewById(R.id.text)).setId(R.id.text + textNum);
        }
        }
    }

    public void textColor(TextView text, int i){
        if(i==0 || i%2==0) {
        text.setBackgroundColor(Color.rgb(100, 230, 200));
    }
    else {
        text.setBackgroundColor(Color.rgb(230, 180, 180));
    }
    }

    public void onBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("WIN", winNum);
        intent.putExtra("LOSE", loseNum);
        intent.putExtra("FIGHT",fightNum);
        for (int i=0; i<fightNum; i++) {
            for (int j = 0; j < 5; j++) {
                intent.putExtra(i + " " + j, history[i][j]);
            }j=0;
        }i=0;
        startActivity(intent);
    }

}

