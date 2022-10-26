package com.example.sample;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


public class Confirm  extends DialogFragment {

    private GlobalArg global;
    int i, j;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        global = (GlobalArg)getActivity().getApplication();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("確認")
                .setMessage("本当に戦績をリセットしますか")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
                        //((TextView)findViewById(R.id.winCount)).setText("" + global.getWinNum());
                        //((TextView)findViewById(R.id.loseCount)).setText("" + global.getLoseNum())
                    }
                })
                .setNegativeButton("No", null);
        return builder.create();
    }

}
