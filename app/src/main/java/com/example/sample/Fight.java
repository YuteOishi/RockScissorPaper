package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Fight extends AppCompatActivity implements View.OnClickListener {
    int n;
    int id;
    int meId;
    String me;
    String enemy;
    View view;
    int winNum;
    int loseNum;
    int i;
    int j;
    int fightNum;
    int aikoNum;
    String[][] history = new String[1000][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        findViewById(R.id.rockView).setOnClickListener(this);
        findViewById(R.id.scissorView).setOnClickListener(this);
        findViewById(R.id.paperView).setOnClickListener(this);

        findViewById(R.id.enemyRockView).setVisibility(View.INVISIBLE);
        findViewById(R.id.enemyScissorView).setVisibility(View.INVISIBLE);
        findViewById(R.id.enemyPaperView).setVisibility(View.INVISIBLE);
        findViewById(R.id.restart).setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        winNum = intent.getIntExtra("WIN",0);
        loseNum = intent.getIntExtra("LOSE",0);
        fightNum = intent.getIntExtra("FIGHT",0);
        for (int i=0; i<fightNum; i++){
            for(int j=0; j<5; j++) {
                history[i][j] = intent.getStringExtra(i + " " + j);
            }j=0;
        }i=0;
    }

    public void onClick(View view) {
        Random random = new Random();
        hideEnemy();
        int n = random.nextInt(3);
        if (n == 0) {
            findViewById(R.id.enemyRockView).setVisibility(View.VISIBLE);
            enemy = "rock";
        } else if (n == 1) {
            findViewById(R.id.enemyScissorView).setVisibility(View.VISIBLE);
            enemy = "scissor";
        } else {
            findViewById(R.id.enemyPaperView).setVisibility(View.VISIBLE);
            enemy = "paper";
        }
        if (view.getId() == R.id.rockView) {
            me = "rock";
            judge();
        } else if (view.getId() == R.id.scissorView) {
            me = "scissor";
            judge();
        } else if (view.getId() == R.id.paperView) {
            me = "paper";
            judge();
        }
    }

    public void onBackPressed() {
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

    public void onRestart(View view) {
        hideEnemy();
        apperMeAll();
        findViewById(meId).setOnClickListener(this);
        ((TextView)findViewById(R.id.text1)).setText("  最初はグー！　\n　じゃんけん！  ");

        findViewById(R.id.restart).setVisibility(View.INVISIBLE);
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

    public void hideEnemy(){
        findViewById(R.id.enemyRockView).setVisibility(View.INVISIBLE);
        findViewById(R.id.enemyScissorView).setVisibility(View.INVISIBLE);
        findViewById(R.id.enemyPaperView).setVisibility(View.INVISIBLE);
    }
      public void hideMe(){
        findViewById(R.id.rockView).setVisibility(View.INVISIBLE);
        findViewById(R.id.scissorView).setVisibility(View.INVISIBLE);
        findViewById(R.id.paperView).setVisibility(View.INVISIBLE);
    }

    public void apperMeAll(){
        findViewById(R.id.rockView).setVisibility(View.VISIBLE);
        findViewById(R.id.scissorView).setVisibility(View.VISIBLE);
        findViewById(R.id.paperView).setVisibility(View.VISIBLE);
    }

    public void apperMe(){
        meId = getResources().getIdentifier(me + "View", "id", getPackageName());
        findViewById(meId).setVisibility(View.VISIBLE);
        findViewById(meId).setOnClickListener(null);
    }

    public void tie(){
        ((TextView)findViewById(R.id.text1)).setText("あいこで～");
        aikoNum++;
    }
    public void win(){
        ((TextView)findViewById(R.id.text1)).setText("勝利！！");
        hideMe();
        apperMe();
        findViewById(R.id.restart).setVisibility(View.VISIBLE);
        history[fightNum][0]=String.valueOf(fightNum+1);
        history[fightNum][1]=getnow();
        history[fightNum][2]="勝敗：勝利";
        history[fightNum][3]="自分："+me+"  相手"+enemy;
        history[fightNum][4]="あいこ："+aikoNum+"回";
        winNum++;
        fightNum++;
        aikoNum=0;
    }
    public void lose(){
        ((TextView)findViewById(R.id.text1)).setText("敗北！！");
        hideMe();
        apperMe();
        findViewById(R.id.restart).setVisibility(View.VISIBLE);
        history[fightNum][0]=String.valueOf(fightNum+1);
        history[fightNum][1]=getnow();
        history[fightNum][2]="勝敗：敗北";
        history[fightNum][3]="自分："+me+"  相手"+enemy;
        history[fightNum][4]="あいこ："+aikoNum+"回";
        loseNum++;
        fightNum++;
        aikoNum=0;
    }
    public void judge(){
        if(me==enemy){
            tie();
        }
        else if((me=="rock"&&enemy=="scissor")||(me=="scissor"&&enemy=="paper")||(me=="paper"&&enemy=="rock")){
            win();
        }
        else
            lose();
    }
    private String getnow() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
        return sdf.format(date);
    }
}