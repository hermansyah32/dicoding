package com.manheadblog.moviecatalogue.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.manheadblog.moviecatalogue.R;


public class SimpleDialog {

    private Context context;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private String title;
    private String message;
    private String buttonPositiveText;
    private String buttonNegativeText;
    private String buttonNeutralText;
    private DialogType dialogType;
    private DialogResult dialogResult;
    private boolean isCancelable = true;

    private DialogInterface.OnClickListener onPosiviteButtonListener;
    private DialogInterface.OnClickListener onNegativeButtonListener;
    private DialogInterface.OnClickListener onNeutralButtonListener;

    public enum DialogType{
        OK_ONLY,
        YES_NO,
        YES_NO_CANCEL,
    }

    public enum DialogResult{
        OK,
        YES,
        NO,
        CANCEL
    }

    public SimpleDialog(@NonNull Context context, DialogType dialogType) {
        this.context = context;
        this.dialogType = dialogType;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder = new AlertDialog.Builder(context);
        }

        init();
    }

    private void init(){
        //Create default value for dialog text
        if (this.title == null){
            builder.setTitle(context.getResources().getString(R.string.dialog_title));
        }

        onNegativeButtonListener = (dialog, which) -> dialog.dismiss();

        buttonPositiveText = context.getResources().getString(R.string.dialog_positiveButton);
        buttonNegativeText = context.getResources().getString(R.string.dialog_negativeButton);
        buttonNeutralText = context.getResources().getString(R.string.dialog_neutralButton);

        switch (this.dialogType){
            case OK_ONLY:
                onPosiviteButtonListener = (dialog, which) -> dialog.dismiss();
                builder.setPositiveButton(buttonPositiveText, onPosiviteButtonListener);
                break;
            case YES_NO:
                builder.setPositiveButton(buttonPositiveText, onPosiviteButtonListener);
                builder.setNegativeButton(buttonNegativeText, onNegativeButtonListener);
                break;
            case YES_NO_CANCEL:
                builder.setPositiveButton(buttonPositiveText, onPosiviteButtonListener);
                builder.setNegativeButton(buttonNegativeText, onNegativeButtonListener);
                builder.setNeutralButton(buttonNeutralText, onNeutralButtonListener);
                break;
        }
    }

    public void setTitle(String title) {
        this.title = title;
        builder.setTitle(title);
    }

    public void setMessage(String message) {
        this.message = message;
        builder.setMessage(message);
    }

    public void setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        builder.setCancelable(cancelable);
    }

    public void setButtonPositiveText(String buttonPositiveText) {
        this.buttonPositiveText = buttonPositiveText;
        builder.setPositiveButton(buttonPositiveText, onPosiviteButtonListener);
    }

    public void setButtonNegativeText(String buttonNegativeText) {
        this.buttonNegativeText = buttonNegativeText;
        builder.setNegativeButton(buttonNegativeText, onNegativeButtonListener);
    }

    public void setButtonNeutralText(String buttonNeutralText) {
        this.buttonNeutralText = buttonNeutralText;
        builder.setPositiveButton(buttonNeutralText, onNegativeButtonListener);
    }

    public void setOnPosiviteButtonListener(DialogInterface.OnClickListener onPosiviteButtonListener) {
        this.onPosiviteButtonListener = onPosiviteButtonListener;
        builder.setPositiveButton(buttonPositiveText, onPosiviteButtonListener);
    }

    public void setOnNegativeButtonListener(DialogInterface.OnClickListener onNegativeButtonListener) {
        this.onNegativeButtonListener = onNegativeButtonListener;
        builder.setNegativeButton(buttonNegativeText, onNegativeButtonListener);
    }

    public void setOnNeutralButtonListener(DialogInterface.OnClickListener onNeutralButtonListener) {
        this.onNeutralButtonListener = onNeutralButtonListener;
        builder.setNeutralButton(buttonNeutralText, onNeutralButtonListener);
    }

    public void show(){
        dialog = builder.create();
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
