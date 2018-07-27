package com.example.student.lab08;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import javax.xml.transform.Result;

public class QuizActivity extends AppCompatActivity {

    public static final int MAX_QUESTION = 3;

    public static final String BUNDLE_KEY_ANSWER_TEMPLATE = "com.example.student.lab08.answer";
//    public static final String BUNDLE_KEY_ANSWER1 = "com.example.student.lab08.answer1";
//    public static final String BUNDLE_KEY_ANSWER2 = "com.example.student.lab08.answer2";
//    public static final String BUNDLE_KEY_ANSWER3 = "com.example.student.lab08.answer3";

    private static final String PACKAGE_NAME = "com.example.student.lab08";
    private static final String TAG = "QuizActivity";
    private String mSelectedAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
        overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);

        init();
    }

    private void init(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // get current question number
        int qNum = bundle.getInt(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER, 0);
        if(qNum == 0){
            Log.d(TAG, "init() didn't find the question number");
        }
        else{
            Log.d(TAG, "init() current question number: " + qNum);
            setQuizContext(qNum);
        }

        // reset radio buttons unchecked, button disabled
        RadioGroup rg = findViewById(R.id.rgroup);
        rg.clearCheck();
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setEnabled(false);
    }

    private void setQuizContext(int qNum){

        // set question number
        TextView tv_qNum = findViewById(R.id.tv_qNum);
        tv_qNum.setText(String.valueOf(qNum));

        // set question content
        String str = getQuestionContent(qNum);
        TextView tv_qContent = findViewById(R.id.tv_qContent);
        tv_qContent.setText(str);

        // set answer contents
        String[] ans = getAnswersContent(qNum);
        RadioButton rb_ansA = findViewById(R.id.rb_ansA);
        RadioButton rb_ansB = findViewById(R.id.rb_ansB);
        RadioButton rb_ansC = findViewById(R.id.rb_ansC);

        if(ans.length >= 3){
            rb_ansA.setText(ans[0]);
            rb_ansB.setText(ans[1]);
            rb_ansC.setText(ans[2]);
        }

    }

    private String getQuestionContent(int qNum) {
        String resourceName = "question" + qNum + "_content";

        int id = getResources()
                    .getIdentifier(resourceName, "string", PACKAGE_NAME);

        // hard-coding
//        switch(qNum){
//            case 1:
//                id = R.string.question1_content;
//                break;
//            case 2:
//                id = R.string.question2_content;
//                break;
//            case 3:
//                id = R.string.question3_content;
//                break;
//            default:
//                id = 0;
//                break;
//        }



        return getString(id);
    }

    private String[] getAnswersContent(int qNum){

        String[] resourceName = new String[3];
        resourceName[0] = "question" + qNum + "_answerA";
        resourceName[1] = "question" + qNum + "_answerB";
        resourceName[2] = "question" + qNum + "_answerC";

        int[] id = new int[3];
        for(int i = 0; i < id.length; i++){
            id[i] = getResources()
                    .getIdentifier(resourceName[i], "string", PACKAGE_NAME);
        }

        // hard-coding
//        switch(qNum){
//            case 1:
//                id[0] = R.string.question1_answerA;
//                id[1] = R.string.question1_answerB;
//                id[2] = R.string.question1_answerC;
//                break;
//            case 2:
//                id[0] = R.string.question2_answerA;
//                id[1] = R.string.question2_answerB;
//                id[2] = R.string.question2_answerC;
//                break;
//            case 3:
//                id[0] = R.string.question3_answerA;
//                id[1] = R.string.question3_answerB;
//                id[2] = R.string.question3_answerC;
//                break;
//            default:
//                id[0] = 0;
//                id[1] = 0;
//                id[2] = 0;
//                break;
//        }

        String[] str = new String[3];

        for(int i = 0; i < str.length; i++){
            str[i] = getString(id[i]);
        }

        return str;
    }

    public void nextQuestion(View view) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int qNum = bundle.getInt(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER, 0);

        if(qNum < MAX_QUESTION) {
            // start a new QuizActivity
            intent.setClass(this, QuizActivity.class);

            // change next question number
            intent.removeExtra(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER);
            intent.putExtra(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER, (qNum + 1));

            // put the selected answer
            intent.putExtra((BUNDLE_KEY_ANSWER_TEMPLATE + qNum), mSelectedAnswer);
            Log.d(TAG, "nextQuestion() put ans" + qNum + ": " + mSelectedAnswer);

            // hard-coding
//            switch (qNum) {
//                case 1:
//                    intent.putExtra(BUNDLE_KEY_ANSWER1, mSelectedAnswer);
//                    Log.d(TAG, "nextQuestion() put ans1: " + mSelectedAnswer);
//                    break;
//                case 2:
//                    intent.putExtra(BUNDLE_KEY_ANSWER2, mSelectedAnswer);
//                    Log.d(TAG, "nextQuestion() put ans2: " + mSelectedAnswer);
//                    break;
//
//            }

            startActivity(intent);
            overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);
        }
        else if(qNum == MAX_QUESTION){
            // start ResultActivity
            intent.setClass(this, ResultActivity.class);

            // put the selected answer
            intent.putExtra((BUNDLE_KEY_ANSWER_TEMPLATE + qNum), mSelectedAnswer);
            Log.d(TAG, "nextQuestion() put ans" + qNum + ": " + mSelectedAnswer);

            startActivity(intent);
            overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);
        }
        else{
            Log.d(TAG, "nextQuestion() question number out of bound");
        }

    }

    public void chooseAnswer(View view) {

        switch(view.getId()){
            case R.id.rb_ansA:
                mSelectedAnswer = "A";
                break;
            case R.id.rb_ansB:
                mSelectedAnswer = "B";
                break;
            case R.id.rb_ansC:
                mSelectedAnswer = "C";
                break;
        }

        // must choose one answer to proceed to next question
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setEnabled(true);

    }
}
