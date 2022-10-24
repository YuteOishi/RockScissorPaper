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
    private GlobalArg global;
    int textNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        global = (GlobalArg)this.getApplication();

        setContentView(R.layout.activity_history);
        layout = findViewById(R.id.historyLinear);

        ((TextView)findViewById(R.id.winMax)).setText("連続勝利記録：　"+global.getWinMax()+"回");
        ((TextView)findViewById(R.id.loseMax)).setText("連続敗北記録：　"+global.getLoseMax()+"回");
        ((TextView)findViewById(R.id.tieMax)).setText("連続あいこ記録："+global.getTieMax()+"回");

        for (int i = 0; i < global.getFightNum(); i++) {
            for (int j = 0; j < 5; j++) {
                View hisview = getLayoutInflater().inflate(R.layout.his1, null);
                View lineview = getLayoutInflater().inflate(R.layout.line, null);
                layout.addView(hisview);
                TextView text = (TextView) findViewById(R.id.text);
                textNum++;
                text.setText(global.getHistory(i,j));
                textColor(text, global.getHistory(i,2));
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
        startActivity(intent);
    }

}

