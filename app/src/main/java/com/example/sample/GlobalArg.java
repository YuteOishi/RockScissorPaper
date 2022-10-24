package com.example.sample;

import android.app.Application;

public class GlobalArg extends Application {

        private int winNum,loseNum,fightNum,tieMax,winMax,loseMax,winConsecutive,loseConsecutive,tieConsecutive;
        String[][] history = new String[1000][5];
        public void onCreate() {
            super.onCreate();
        }

        public String getHistory(int i, int j){
            return history[i][j];
        }
        public void setHistory(String his,int i, int j) {
            history[i][j] = his;
        }
        public int getWinNum() {
            return winNum;
        }
        public void setWinNum(int winN) {
            winNum = winN;
        }
        public int getLoseNum() {
            return loseNum;
        }
        public void setLoseNum(int loseN) {
            loseNum = loseN;
        }
        public int getFightNum() {
            return fightNum;
        }
        public void setFightNum(int fightN) {
            fightNum = fightN;
        }
        public int getWinMax() {
            return winMax;
        }
        public void setWinMax(int winM) {
            winMax = winM;
        }
         public int getLoseMax() {
        return loseMax;
          }
        public void setLoseMax(int loseM) {
            loseMax = loseM;
        }
        public int getTieMax() {
            return tieMax;
        }
        public void setTieMax(int tieM) {
            tieMax = tieM;
        }
        public int getWinConsecutive() {
            return winConsecutive;
        }
        public void setWinConsecutive(int winC) {
            winConsecutive = winC;
        }
        public int getLoseConsecutive() {
            return loseConsecutive;
        }
        public void setLoseConsecutive(int loseC) {
            loseConsecutive = loseC;
        }
        public int getTieConsecutive() {
            return tieConsecutive;
        }
        public void setTieConsecutive(int tieC) {
            tieConsecutive = tieC;
        }

}


