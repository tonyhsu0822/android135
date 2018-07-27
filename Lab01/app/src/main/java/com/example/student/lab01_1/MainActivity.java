package com.example.student.lab01_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lab01_3_linearlayout);
    }

    public void contextSwitch(View v){
        Intent i = new Intent();
        i.setClass(this, MainActivity2.class);
        startActivity(i);
    }
}
