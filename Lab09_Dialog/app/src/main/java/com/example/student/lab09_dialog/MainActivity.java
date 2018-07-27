package com.example.student.lab09_dialog;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener, MyDialogFragment.MyDialogFragmentInteraction{


    private TextView tv_message;
    private int mChoice;

    public TextView getTv_message() {
        return tv_message;
    }

    @Override
    public void signIn(String username) {
        tv_message.setText("Welcome " + username + "!");
    }

    @Override
    public void cancel() {
        tv_message.setText("Login was canceled");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
         tv_message = findViewById(R.id.tv_message);
    }

    public void clickAlertDialogOK(View view) {

        new AlertDialog.Builder(this)
                .setTitle("Hello!")
                .setMessage("You are handsome!")
                .setPositiveButton("I know", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        tv_message.setText("I know");
    }

    public void clickAlertDialogYesNo(View view) {
        DialogInterface.OnClickListener listener = new AlertDialogYesNoListener();

        new AlertDialog.Builder(this)
                .setMessage("You are handsome")
                .setPositiveButton("Thank you", listener)
                .setNegativeButton("Are you kidding?", listener)
                .show();
    }



    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch(i){
                case DialogInterface.BUTTON_POSITIVE:
                    tv_message.setText("Thank you");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    tv_message.setText("Fxck you");
                    break;
            }
        }
    }

    public void clickAlertDialogYesNoCancel(View view) {

        new AlertDialog.Builder(this)
                .setMessage("You are handsome")
                .setPositiveButton("Thank you", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText("Thank you");
                    }
                })
                .setNegativeButton("No way", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText("Fxck you");
                    }
                })
                .setNeutralButton("......", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText("......");
                    }
                })
                .show();
    }

    public void clickAlertDialogItems(View view) {

        final String[] itemList = getResources().getStringArray(R.array.alert_dialog_response);

        new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_NoActionBar)
                .setTitle("You are handsome")
                .setItems(itemList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText(itemList[i]);
                    }
                })
                .setPositiveButton("OKOK", this)
                .show();

    }

    public void clickAlertDialogMultiChoice(View view) {

        final String[] itemList = getResources().getStringArray(R.array.alert_dialog_response);
        final boolean[] selected = new boolean[itemList.length];

        new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_NoActionBar)
                .setTitle("You are handsome")
                .setMultiChoiceItems(itemList, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < selected.length; i++){
                            if(selected[i]){
                                sb.append(itemList[i]).append("\n");
                            }
                        }

                        tv_message.setText(sb.toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText("......");
                    }
                })
                .show();
    }

    public void clickAlertDialogSingleChoice(View view) {

        final String[] itemList = getResources().getStringArray(R.array.alert_dialog_response);
        mChoice = 0;

        new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
                .setTitle("Hello")
                .setSingleChoiceItems(itemList, mChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mChoice = i;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText(itemList[mChoice]);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_message.setText("......");
                    }
                })
                .show();
    }

    public void clickAlertDialogMyDialogFragment(View view) {

        MyDialogFragment dialog = new MyDialogFragment();
        dialog.show(getSupportFragmentManager(), "tag");

    }
}
