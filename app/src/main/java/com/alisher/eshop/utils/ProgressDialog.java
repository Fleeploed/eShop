package com.alisher.eshop.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.alisher.eshop.R;

public class ProgressDialog {

    private ProgressDialog(){}

    public static AlertDialog createAlertDialog(Activity activity){
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.custom_progress_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialogLayout);
        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().setLayout(600, 300);
        return alert;
    }
}
