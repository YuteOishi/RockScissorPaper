package com.example.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Confirm  extends DialogFragment {
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("確認")
                .setMessage("本当に戦績をリセットしますか")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("YES", null)
                .setNeutralButton("Cancel", null);
        return builder.create();
    }

}
