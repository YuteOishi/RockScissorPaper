package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
        setHistory();
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.winCount)).setText(""+global.getWinNum());
        ((TextView)findViewById(R.id.loseCount)).setText(""+global.getLoseNum());
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_open_settings:
                startActivity(new Intent(this, Setting.class));
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public void onStart(View view) {
        changeActivityWithHistory(Fight.class);
    }

    public void onHistory(View view) {
        changeActivityWithHistory(History.class);
    }

    public void onReset(View view) {
        //DialogFragment confirm = new Confirm();
        //confirm.show(getSupportFragmentManager(), "confirm");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        setResetConfirm(builder);
        ((TextView) findViewById(R.id.winCount)).setText("" + global.getWinNum());
        ((TextView) findViewById(R.id.loseCount)).setText("" + global.getLoseNum());

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

    public void setHistory(){
        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        global.setWinNum(sharedPreferences.getInt("WIN_NUM", 0));
        global.setLoseNum(sharedPreferences.getInt("LOSE_NUM", 0));
        global.setFightNum(sharedPreferences.getInt("FIGHT_NUM", 0));
        global.setWinMax(sharedPreferences.getInt("WIN_MAX", 0));
        global.setLoseMax(sharedPreferences.getInt("LOSE_MAX", 0));
        global.setTieMax(sharedPreferences.getInt("TIE_MAX", 0));
        global.setWinConsecutive(sharedPreferences.getInt("WIN_CONSECUTIVE", 0));
        global.setLoseConsecutive(sharedPreferences.getInt("LOSE_CONSECUTIVE", 0));
        global.setTieConsecutive(sharedPreferences.getInt("TIE_CONSECUTIVE", 0));
        for (int i = 0; i < global.getFightNum(); i++) {
            for (int j = 0; j < 5; j++) {
                global.setHistory(sharedPreferences.getString("HISTORY"+ i + j,"DEFAULT"),i,j);
            }
            j = 0;
        }
        i = 0;
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
                global.setTieMax(0);
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
                SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("WIN_NUM",global.getWinNum());
                editor.putInt("LOSE_NUM",global.getLoseNum());
                editor.putInt("FIGHT_NUM",global.getFightNum());
                editor.putInt("WIN_MAX",global.getWinMax());
                editor.putInt("LOSE_MAX",global.getLoseMax());
                editor.putInt("TIE_MAX",global.getTieMax());
                editor.putInt("WIN_CONSECUTIVE",global.getWinConsecutive());
                editor.putInt("LOSE_CONSECUTIVE",global.getLoseConsecutive());
                editor.putInt("TIE_CONSECUTIVE",global.getTieConsecutive());
                for (int i = 0; i < global.getFightNum(); i++) {
                    for (int j = 0; j < 5; j++) {
                        editor.putString("HISTORY"+ i + j,global.getHistory(i,j));
                    }
                    j = 0;
                }
                i = 0;
                editor.apply();
            }
        });
        builder.create();
        builder.show();
    }

    }

