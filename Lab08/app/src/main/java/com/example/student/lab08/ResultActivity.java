package com.example.student.lab08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        showResult();
    }

    private void showResult(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // get answer of each question
        String ans;
        StringBuilder sb = new StringBuilder();

        for(int qNum = 1; qNum <= QuizActivity.MAX_QUESTION; qNum++){
            ans = bundle.getString((QuizActivity.BUNDLE_KEY_ANSWER_TEMPLATE + qNum), null);
            if(ans == null){
                Log.d(TAG, "showResult() ans" + qNum + " is null");
            }
            else{
                sb.append(qNum + ". ")
                        .append(ans)
                        .append("\n");
            }
        }
        // hard-coding
//        String ans1 = bundle.getString(QuizActivity.BUNDLE_KEY_ANSWER1, null);
//        String ans2 = bundle.getString(QuizActivity.BUNDLE_KEY_ANSWER2, null);
//        String ans3 = bundle.getString(QuizActivity.BUNDLE_KEY_ANSWER3, null);


//        for(int i = 0, qNum = 1; i < ans.length; i++, qNum++){
//            sb.append(qNum + ". ")
//                    .append(ans[i])
//                    .append("\n");
//        }

//            sb.append("1. ")
//                    .append(ans1)
//                    .append("\n")
//                    .append("2. ")
//                    .append(ans2)
//                    .append("\n")
//                    .append("3. ")
//                    .append(ans3);
        TextView tv_result = findViewById(R.id.tv_result);
        tv_result.setText(sb);
    }

    public void backToTitle(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);
    }
}
