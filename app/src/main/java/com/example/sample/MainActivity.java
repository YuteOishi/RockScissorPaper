package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int winNum,loseNum,i,j,fightNum,tieMax,winMax,loseMax,winConsecutive,loseConsecutive,tieConsecutive;
    boolean reset;
    String[][] history = new String[1000][5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        winNum = intent.getIntExtra("WIN",0);
        loseNum = intent.getIntExtra("LOSE",0);
        fightNum = intent.getIntExtra("FIGHT",0);
        winMax = intent.getIntExtra("WINMAX",0);
        loseMax = intent.getIntExtra("LOSEMAX",0);
        tieMax = intent.getIntExtra("TIEMAX",0);
        winConsecutive = intent.getIntExtra("WINCONSECUTIVE",0);
        loseConsecutive = intent.getIntExtra("LOSECONSECUTIVE",0);
        tieConsecutive = intent.getIntExtra("TIECONSECUTIVE",0);
        for (int i=0; i<fightNum; i++){
            for(int j=0; j<5; j++) {
                history[i][j] = intent.getStringExtra(i + " " + j);
            }j=0;
        }i=0;
        ((TextView)findViewById(R.id.winCount)).setText(""+winNum);
        ((TextView)findViewById(R.id.loseCount)).setText(""+loseNum);
    }


    public void onStart(View view) {
        changeActivityWithHistory(Fight.class);
    }

    public void onHistory(View view) {
        changeActivityWithHistory(History.class);
    }

    public void onReset(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        setResetConfirm(builder);
    }

    public void changeActivityWithHistory(Class intentClass){
        Intent intent = new Intent(this, intentClass);
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

    public void onFin(View view){
        moveTaskToBack (true);
    }

    public void onBackPressed() {
        moveTaskToBack (true);
    }

    public void setResetConfirm(AlertDialog.Builder builder){
        builder.setTitle("本当に戦績リセットしますか？");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                winNum = 0;
                loseNum = 0;
                fightNum = 0;
                winMax = 0;
                loseMax = 0;
                tieMax = 0;
                winConsecutive = 0;
                loseConsecutive = 0;
                tieConsecutive = 0;
                for (int i = 0; i < fightNum; i++) {
                    for (int j = 0; j < 5; j++) {
                        history[i][j] = null;
                    }
                    j = 0;
                }
                i = 0;
                ((TextView) findViewById(R.id.winCount)).setText("" + winNum);
                ((TextView) findViewById(R.id.loseCount)).setText("" + loseNum);
            }
        });
        builder.create();
        builder.show();
    }

    }

