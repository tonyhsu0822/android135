package com.example.student.lab07_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ImagePickerActivity extends AppCompatActivity {


    public static final String BUNDLE_KEY_IMAGE_ID = "com.example.student.sourceImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
    }

    public void selectImage(View view) {

        int imgId;

        switch(view.getId()){
            case R.id.ib_hornets:
                imgId = R.drawable.hornets;
                break;
            case R.id.ib_rockets:
                imgId = R.drawable.rockets;
                break;
            default:
                imgId = 0;
                break;
        }


        if(imgId != 0) {
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_KEY_IMAGE_ID, imgId);
            setResult(RESULT_OK, intent);
        }
        else{
            setResult(RESULT_CANCELED);
        }
        finish();

    }
}
