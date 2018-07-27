package com.example.student.lab01_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lab01_5_practice);
    }

    public void layoutSwitch(View v){
        setContentView(R.layout.layout_lab01_5_2);
    }
}
