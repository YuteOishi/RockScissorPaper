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
    int winNum,loseNum,textNum,i,j,fightNum,tieMax,winMax,loseMax,winConsecutive,loseConsecutive,tieConsecutive;
    String[][] history = new String[1000][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        layout = findViewById(R.id.historyLinear);
        Intent intent = getIntent();
        winNum = intent.getIntExtra("WIN", 0);
        loseNum = intent.getIntExtra("LOSE", 0);
        fightNum = intent.getIntExtra("FIGHT", 0);
        winMax = intent.getIntExtra("WINMAX",0);
        loseMax = intent.getIntExtra("LOSEMAX",0);
        tieMax = intent.getIntExtra("TIEMAX",0);
        winConsecutive = intent.getIntExtra("WINCONSECUTIVE",0);
        loseConsecutive = intent.getIntExtra("LOSECONSECUTIVE",0);
        tieConsecutive = intent.getIntExtra("TIECONSECUTIVE",0);
        for (int i = 0; i < fightNum; i++) {
            for (int j = 0; j < 5; j++) {
                history[i][j] = intent.getStringExtra(i + " " + j);
            }
            j = 0;
        }
        i = 0;

        ((TextView)findViewById(R.id.winMax)).setText("連続勝利記録：　"+winMax+"回");
        ((TextView)findViewById(R.id.loseMax)).setText("連続敗北記録：　"+loseMax+"回");
        ((TextView)findViewById(R.id.tieMax)).setText("連続あいこ記録："+loseMax+"回");

        for (int i = 0; i < fightNum; i++) {
            for (int j = 0; j < 5; j++) {
                View hisview = getLayoutInflater().inflate(R.layout.his1, null);
                View lineview = getLayoutInflater().inflate(R.layout.line, null);
                layout.addView(hisview);
                TextView text = (TextView) findViewById(R.id.text);
                textNum++;
                text.setText(history[i][j]);
                textColor(text, history[i][2]);
                ((TextView) findViewById(R.id.text)).setId(R.id.text + textNum);
                if(j==4){
                    layout.addView(lineview);
                }
            }
        }
    }


    public void textColor(TextView text, String winJudge){
        if(winJudge.equals("勝敗：勝利")){
            text.setBackgroundColor(Color.rgb(230, 180, 180));
        }
        else{
            text.setBackgroundColor(Color.rgb(100, 230, 200));
        }
    }

    public void onBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("WIN", winNum);
        intent.putExtra("LOSE", loseNum);
        intent.putExtra("FIGHT",fightNum);
        intent.putExtra("WINMAX",winMax);
        intent.putExtra("LOSEMAX",loseMax);
        intent.putExtra("TIEMAX",tieMax);
        intent.putExtra("WINCONSECUTIVE",winConsecutive);
        intent.putExtra("LOSECONSECUTIVE",loseConsecutive);
        intent.putExtra("TIECONSECUTIVE",tieConsecutive);
        for (int i=0; i<fightNum; i++) {
            for (int j = 0; j < 5; j++) {
                intent.putExtra(i + " " + j, history[i][j]);
            }j=0;
        }i=0;
        startActivity(intent);
    }

}

