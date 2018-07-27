package com.example.student.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends AppCompatActivity {

    private static final String TAG = "ColorPickerActivity";

    public static final String BUNDLE_KEY_COLOR_INT = "com.example.student.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.student.colorName";
    private static final String BUNDLE_KEY_RADIO_BUTTON_ID = "com.example.student.radioButtonId";

    private int mColorInt;
    private CharSequence mColorName;
    private int mRadioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        initSelectColor();
    }

    private void initSelectColor(){
        RadioButton rb_red = findViewById(R.id.rb_red);
        rb_red.setChecked(true);
//        clickColor(rb_red);
        mColorInt = rb_red.getCurrentTextColor();
        mColorName = rb_red.getText();
        mRadioButtonId = rb_red.getId();
        Log.d(TAG, "initSelectColor() mColorInt = " + mColorInt + ", mColorName = " + mColorName);
    }

    private void saveData(){
        // get preference
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        // get editor
        SharedPreferences.Editor editor = pref.edit();
        // put data
        editor.putInt(BUNDLE_KEY_COLOR_INT, mColorInt);
        editor.putString(BUNDLE_KEY_COLOR_NAME, mColorName.toString());
        editor.putInt(BUNDLE_KEY_RADIO_BUTTON_ID, mRadioButtonId);
        // send data
        editor.commit();

        Log.d(TAG, "saveData() mColorInt = " + mColorInt + ", mColorName = " + mColorName);
    }

    private void loadData(){
        // get preference
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        // get data
        // 2nd arg: default value when the key is not found
        mColorInt = pref.getInt(BUNDLE_KEY_COLOR_INT, 0);
        mColorName = pref.getString(BUNDLE_KEY_COLOR_NAME, "holo_red_light");
        mRadioButtonId = pref.getInt(BUNDLE_KEY_RADIO_BUTTON_ID, 0);

        RadioButton rb = findViewById(mRadioButtonId);
        if(rb != null) {
            rb.setChecked(true);
        }
//        int id;
//        switch(mColorName.toString()) {
//            case "holo_red_light":
//                id = R.id.rb_red;
//                break;
//            case "holo_orange_dark":
//                id = R.id.rb_orange;
//                break;
//            case "holo_green_light":
//                id = R.id.rb_green;
//                break;
//            case "holo_blue_dark":
//                id = R.id.rb_blue;
//                break;
//            default:
//                id = 0;
//        }
//
//        if(id != 0){
//            RadioButton rb = findViewById(id);
//            rb.setChecked(true);
//        }
//        else{
//            Log.d(TAG, "loadData() illegal color name");
//        }

        Log.d(TAG, "loadData() mColorInt = " + mColorInt + ", mColorName = " + mColorName);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
        saveData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
        loadData();
    }


    public void clickColor(View view) {

        RadioButton rb = (RadioButton) view;
        mColorInt = rb.getCurrentTextColor();
        mColorName = rb.getText();
        mRadioButtonId = rb.getId();
    }


    public void onClickForward(View view) {
        int id = view.getId();

        switch(id){
            case R.id.btn_cancel:
                cancelSelectColor(view);
                break;
            case R.id.btn_ok:
                confirmSelectColor(view);
                break;
        }

    }

    private void cancelSelectColor(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    private void confirmSelectColor(View view){
        Intent intent = new Intent();

        intent.putExtra(BUNDLE_KEY_COLOR_INT, mColorInt);
        intent.putExtra(BUNDLE_KEY_COLOR_NAME, mColorName);
        setResult(RESULT_OK, intent);
        Log.d(TAG, "confirmSelectColor()");
        finish();
    }
}
