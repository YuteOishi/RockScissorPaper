package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int winNum;
    int loseNum;
    int i;
    int j;
    int fightNum;
    String[][] history = new String[1000][5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        winNum = intent.getIntExtra("WIN",0);
        loseNum = intent.getIntExtra("LOSE",0);
        ((TextView)findViewById(R.id.winCount)).setText(""+winNum);
        ((TextView)findViewById(R.id.loseCount)).setText(""+loseNum);
        fightNum = intent.getIntExtra("FIGHT",0);
        for (int i=0; i<fightNum; i++){
            for(int j=0; j<5; j++) {
                history[i][j] = intent.getStringExtra(i + " " + j);
            }j=0;
        }i=0;
    }

    public void onStart(View view) {
        Intent intent = new Intent(this, Fight.class);
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

    public void onHistory(View view) {
        Intent intent = new Intent(this, History.class);
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

    public void onReset(View view) {
        winNum=0;
        loseNum=0;
        fightNum=0;
        for (int i=0; i<fightNum; i++) {
            for (int j = 0; j < 5; j++) {
                history[i][j]=null;
            }j=0;
        }i=0;
        ((TextView)findViewById(R.id.winCount)).setText(""+winNum);
        ((TextView)findViewById(R.id.loseCount)).setText(""+loseNum);
    }

    public void onFin(View view){
        moveTaskToBack (true);
    }

    }

