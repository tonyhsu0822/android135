package com.example.student.lab08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String BUNDLE_KEY_NEXT_QUESTION_NUMBER = "com.example.student.lab08.nextQuestionNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);

        Bundle bundle = intent.getExtras();
        if(bundle != null && bundle.containsKey(BUNDLE_KEY_NEXT_QUESTION_NUMBER)) {
            Log.d(TAG, "startQuiz() extra key QUESTION_NUMBER already existed, "
                    + "value is " + bundle.getInt(BUNDLE_KEY_NEXT_QUESTION_NUMBER));
        }
        else{
            intent.putExtra(BUNDLE_KEY_NEXT_QUESTION_NUMBER, 1);
        }

        // if the target activity already existed in activity stack
        // re-order it to foreground
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
