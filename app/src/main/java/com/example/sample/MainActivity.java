package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int winNum;
    int loseNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        winNum = intent.getIntExtra("WIN",0);
        loseNum = intent.getIntExtra("LOSE",0);
        ((TextView)findViewById(R.id.winCount)).setText(""+winNum);
        ((TextView)findViewById(R.id.loseCount)).setText(""+loseNum);
    }

    public void onStart(View view) {
        Intent intent = new Intent(this, Fight.class);
        intent.putExtra("WIN", winNum);
        intent.putExtra("LOSE", loseNum);
        startActivity(intent);
    }

    }

