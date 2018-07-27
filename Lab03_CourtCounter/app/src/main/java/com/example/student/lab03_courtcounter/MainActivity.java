package com.example.student.lab03_courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mScoreTeamA = 0;
    private int mScoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void point3ForTeamA(View view) {
        addScore(true, 3);
    }

    public void point2ForTeamA(View view) {
        addScore(true, 2);
    }

    public void point1ForTeamA(View view) {
        addScore(true, 1);
    }

    public void point3ForTeamB(View view) {
        addScore(false, 3);
    }

    public void point2ForTeamB(View view) {
        addScore(false, 2);
    }

    public void point1ForTeamB(View view) {
        addScore(false, 1);
    }

    public void resetScore(View view) {
        mScoreTeamA = 0;
        mScoreTeamB = 0;
        display(mScoreTeamA, mScoreTeamB);
    }

    private void addScore(boolean teamA, int add){
        if(teamA){
            mScoreTeamA += add;
        }
        else{
            mScoreTeamB += add;
        }
        display(mScoreTeamA, mScoreTeamB);
    }

    private void display(int score1, int score2){
        TextView tv_score_teamA = findViewById(R.id.tv_score_teamA);
        TextView tv_score_teamB = findViewById(R.id.tv_score_teamB);
        tv_score_teamA.setText(String.valueOf(score1));
        tv_score_teamB.setText(String.valueOf(score2));
    }
}
