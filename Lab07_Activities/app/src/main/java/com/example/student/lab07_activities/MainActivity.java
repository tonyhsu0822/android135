package com.example.student.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int SELECT_COLOR_REQUEST = 0;
    private static final int SELECT_IMAGE_REQUEST = 1;

    private int mColorInt;
    private CharSequence mColorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
//        if(mColorName != null) {
//            saveData();
//        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
        loadData();
    }

    private void saveData(){
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT, mColorInt);
        editor.putString(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME, mColorName.toString());

        editor.commit();

        Log.d(TAG, "saveData() mColorInt = " + mColorInt + ", mColorName = " + mColorName);
    }

    private void loadData(){
        Log.d(TAG, "loadData() current data: mColorInt = " + mColorInt + ", mColorName = " + mColorName);

        SharedPreferences pref = getPreferences(MODE_PRIVATE);

        mColorInt = pref.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT, 0);
        mColorName = pref.getString(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME, null);

        if(mColorInt != 0 && mColorName != null) {
            TextView tv_color = findViewById(R.id.tv_color);
            tv_color.setText(mColorName);
            tv_color.setBackgroundColor(mColorInt);
        }
        else{
            Log.d(TAG, "loadData() No previous data");
        }

//        int colorInt = pref.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT, 0);
//        CharSequence colorName = pref.getString(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME, null);

//        if(colorInt != 0 && colorName != null) {
//            Log.d(TAG, "loadData() load previous data");
//            mColorInt = colorInt;
//            mColorName = colorName;
//            TextView tv_color = findViewById(R.id.tv_color);
//            tv_color.setText(mColorName);
//            tv_color.setBackgroundColor(mColorInt);
//        }
//        else{
//            Log.d(TAG, "loadData() No previous data");
//        }

        Log.d(TAG, "loadData() mColorInt = " + mColorInt + ", mColorName = " + mColorName);
    }

    public void onClickForward(View view) {

        int id = view.getId();

        switch(id){
            case R.id.btn_selectColor:
                selectColor(view);
                break;
            case R.id.btn_selectImage:
                selectImage(view);
                break;
        }

    }

    private void selectColor(View view){
        Intent intent = new Intent(this, ColorPickerActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, SELECT_COLOR_REQUEST);
    }

    private void selectImage(View view){
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult()");

        switch(requestCode){
            case SELECT_COLOR_REQUEST:
                if(resultCode == RESULT_OK)
                    setColor(data);
                break;
            case SELECT_IMAGE_REQUEST:
                if(resultCode == RESULT_OK)
                    setImage(data);
                break;
        }
    }

    private void setColor(@Nullable Intent data) {
        Bundle bundle = data.getExtras();
        mColorInt = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
        mColorName = bundle.getCharSequence(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);

        saveData();

//        TextView tv_color = findViewById(R.id.tv_color);
//        tv_color.setText(mColorName);
//        tv_color.setBackgroundColor(mColorInt);
    }

    private void setImage(@Nullable Intent data){
        Bundle bundle = data.getExtras();
        int imgId = bundle.getInt(ImagePickerActivity.BUNDLE_KEY_IMAGE_ID);

        ImageView iv_teamLogo = findViewById(R.id.iv_teamLogo);
        iv_teamLogo.setImageResource(imgId);
    }
}
