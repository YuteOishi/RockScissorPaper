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
    int i,j,meId,winNum,loseNum,fightNum,tieNum;
    String me,enemy,winLoseText;
    String[][] history = new String[1000][5];
    boolean winLose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        //自分の手札にクリックリスナー付与
        findViewById(R.id.rockView).setOnClickListener(this);
        findViewById(R.id.scissorView).setOnClickListener(this);
        findViewById(R.id.paperView).setOnClickListener(this);

        //相手（真ん中）の表示、「もう一度」を隠す
        findViewById(R.id.enemyRockView).setVisibility(View.INVISIBLE);
        findViewById(R.id.enemyScissorView).setVisibility(View.INVISIBLE);
        findViewById(R.id.enemyPaperView).setVisibility(View.INVISIBLE);
        findViewById(R.id.restart).setVisibility(View.INVISIBLE);

        //戦績情報を受取り
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

    //自分の手札を押したとき、乱数を発生させてそれに応じて相手のグー、チョキ、パーを決めてenemyに代入
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

        //自分がタップしたものを認識してmeに代入、勝ち負け判定、戦績情報記入、等は全てjudgeメソッドで処理
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

    //meとenemyが同じならあいこ、winで勝ちのパターン定義、elseで負けを定義
    public void judge(){
        if(me==enemy){
            tie();
        }
        else if((me=="rock"&&enemy=="scissor")||(me=="scissor"&&enemy=="paper")||(me=="paper"&&enemy=="rock")){
            winLose(true);
        }
        else
            winLose(false);
    }
    //あいこのときの処理
    public void tie(){
        ((TextView)findViewById(R.id.text1)).setText("あいこで～");
        tieNum++;
    }
    //勝ち負け処理, winLoseがtrueで勝ち、falseで負け
    public void winLose(Boolean winLose){
        if(winLose==true) {
            winLoseText = "勝利";
            winNum++;
        }
        else if(winLose==false){
            winLoseText = "敗北";
            loseNum++;
        }
        //戦績をhistory配列にセット
        ((TextView)findViewById(R.id.text1)).setText(winLoseText+"！！");
        hideMeAll();
        apperMe();
        findViewById(R.id.restart).setVisibility(View.VISIBLE);
        history[fightNum][0]="第"+(fightNum+1)+"戦目";
        history[fightNum][1]=getnow();
        history[fightNum][2]="勝敗："+winLoseText;
        history[fightNum][3]="自分："+me+"  相手"+enemy;
        history[fightNum][4]="あいこ："+tieNum+"回";
        tieNum=0;
        fightNum++;
    }
    //勝敗後にもう一度遊ぶ
    public void onRestart(View view) {
        hideEnemy();
        apperMeAll();
        findViewById(meId).setOnClickListener(this);
        ((TextView)findViewById(R.id.text1)).setText("  最初はグー！　\n　じゃんけん！  ");

        findViewById(R.id.restart).setVisibility(View.INVISIBLE);
    }
    //戻るウィジェット、デバイスの戻るボタンを押した場合に戦績情報を引き継ぐ
    public void onBack(View view) {
        backMainWithHistory();
    }
    public void onBackPressed() {
        backMainWithHistory();
    }
    //戦績を引き継いでメインに戻るメソッド
    public void backMainWithHistory() {
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
    public void hideMeAll(){
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
    public String getnow() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
        return sdf.format(date);
    }

}