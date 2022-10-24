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

    private GlobalArg global;
    int i, j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        global = (GlobalArg)this.getApplication();
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.winCount)).setText(""+global.getWinNum());
        ((TextView)findViewById(R.id.loseCount)).setText(""+global.getLoseNum());
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
        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                global.setWinNum(0);
                global.setLoseNum(0);
                global.setFightNum(0);
                global.setWinMax(0);
                global.setLoseMax(0);
                global.setLoseMax(0);
                global.setWinConsecutive(0);
                global.setLoseConsecutive(0);
                global.setTieConsecutive(0);
                for (int i = 0; i < global.getFightNum(); i++) {
                    for (int j = 0; j < 5; j++) {
                        global.setHistory(null,i,j);
                    }
                    j = 0;
                }
                i = 0;
                ((TextView) findViewById(R.id.winCount)).setText("" + global.getWinNum());
                ((TextView) findViewById(R.id.loseCount)).setText("" + global.getLoseNum());
            }
        });
        builder.create();
        builder.show();
    }

    }

