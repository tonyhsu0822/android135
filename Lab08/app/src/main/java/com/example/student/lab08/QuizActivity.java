package com.example.student.lab08;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    public static final int MAX_QUESTION = 3;

    public static final String BUNDLE_KEY_ANSWER_TEMPLATE = "com.example.student.lab08.answer";

    private TextView tv_qNum;
    private TextView tv_qContent;
    private RadioGroup radioGroup;
    private RadioButton rb_ansA;
    private RadioButton rb_ansB;
    private RadioButton rb_ansC;
    private Button btn_next;

    private static final String PACKAGE_NAME = "com.example.student.lab08";
    private static final String TAG = "QuizActivity";
    private String mSelectedAnswer;
    private int mQuestionNo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate()");

        initView();
        init();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
        overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);


    }

    private void initView() {
        tv_qNum = findViewById(R.id.tv_qNum);
        tv_qContent = findViewById(R.id.tv_qContent);
        radioGroup = findViewById(R.id.rgroup);
        rb_ansA = findViewById(R.id.rb_ansA);
        rb_ansB = findViewById(R.id.rb_ansB);
        rb_ansC = findViewById(R.id.rb_ansC);
        btn_next = findViewById(R.id.btn_next);
    }

    private void init(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // get current question number
        mQuestionNo = bundle.getInt(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER, 0);
        if(mQuestionNo == 0){
            Log.d(TAG, "init() didn't find the question number");
        }
        else{
            Log.d(TAG, "init() current question number: " + mQuestionNo);
            setQuizContext(mQuestionNo);
        }

        // reset radio buttons unchecked, button disabled
        radioGroup.clearCheck();
        btn_next.setEnabled(false);
    }

    private void setQuizContext(int qNum){

        // set question number
        tv_qNum.setText(String.valueOf(qNum));

        // set question content
        String str = getQuestionContent(qNum);
        Spanned text = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)?
                        Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY) : Html.fromHtml(str);
        tv_qContent.setText(text);

        // set answer contents
        String[] ans = getAnswersContent(qNum);
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

        String[] str = new String[3];

        for(int i = 0; i < str.length; i++){
            str[i] = getString(id[i]);
        }

        return str;
    }

    public void nextQuestion(View view) {
        Intent intent = getIntent();

        if(mQuestionNo < MAX_QUESTION) {
            // start a new QuizActivity
            intent.setClass(this, QuizActivity.class);

            // change next question number
            intent.removeExtra(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER);
            intent.putExtra(MainActivity.BUNDLE_KEY_NEXT_QUESTION_NUMBER, (mQuestionNo + 1));

            // put the selected answer
            intent.putExtra((BUNDLE_KEY_ANSWER_TEMPLATE + mQuestionNo), mSelectedAnswer);
            Log.d(TAG, "nextQuestion() put ans" + mQuestionNo + ": " + mSelectedAnswer);

            startActivity(intent);
            overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);
        }
        else if(mQuestionNo == MAX_QUESTION){
            // start ResultActivity
            intent.setClass(this, ResultActivity.class);

            // put the selected answer
            intent.putExtra((BUNDLE_KEY_ANSWER_TEMPLATE + mQuestionNo), mSelectedAnswer);
            Log.d(TAG, "nextQuestion() put ans" + mQuestionNo + ": " + mSelectedAnswer);

            startActivity(intent);
            overridePendingTransition(R.anim.in_from_back, R.anim.out_to_back);
        }
        else{
            Log.d(TAG, "nextQuestion() question number out of bound");
        }

    }

    public void lastQuestion(View view) {
        finish();
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
        btn_next.setEnabled(true);
    }
}
