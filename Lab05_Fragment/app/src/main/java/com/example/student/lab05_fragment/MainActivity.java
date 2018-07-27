package com.example.student.lab05_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private CourtCounterFragment frag_teamA;
    private CourtCounterFragment frag_teamB;
    private TextView tv_teamNameA;
    private TextView tv_scoreTeamA;
    private TextView tv_scoreTeamB;

    private int mScoreTeamA = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // can not get fragment at here.
        // fragment is not yet created.
        /*
        frag_teamA = (CourtCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_teamA);
        tv_teamNameA = frag_teamA.getTv_teamName();
        tv_scoreTeamA = frag_teamA.getTv_score();
        */

    }

    @Override
    protected void onStart() {
        super.onStart();

        frag_teamA = (CourtCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_teamA);
        frag_teamB = (CourtCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_teamB);

        String teamAName = getString(R.string.team_a_name);
        String teamBName = getString(R.string.team_b_name);

        frag_teamA.setTeamName(teamAName);
        frag_teamB.setTeamName(teamBName);

        frag_teamA.setTeamLogo(R.drawable.team_a_logo);
        frag_teamB.setTeamLogo(R.drawable.team_b_logo);
    }

    public void add3Points(View view){
        /*
        frag_teamA = (CourtCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_teamA);
        tv_scoreTeamA = frag_teamA.getTv_score();
        */
        LinearLayout ll = (LinearLayout)view.getParent();
        tv_scoreTeamA = ll.findViewById(R.id.tv_score);
//        mScoreTeamA += 3;
//        display();
        int s = Integer.parseInt(tv_scoreTeamA.getText().toString());
        tv_scoreTeamA.setText(String.valueOf(s + 3));
    }


    public void add2Points(View view){

        LinearLayout ll = (LinearLayout)view.getParent();
        tv_scoreTeamA = ll.findViewById(R.id.tv_score);
//        mScoreTeamA += 2;
//        display();
        int s = Integer.parseInt(tv_scoreTeamA.getText().toString());
        tv_scoreTeamA.setText(String.valueOf(s + 2));
    }

    public void add1Point(View view){

        LinearLayout ll = (LinearLayout)view.getParent();
        tv_scoreTeamA = ll.findViewById(R.id.tv_score);
//        mScoreTeamA ++;
//        display();
        int s = Integer.parseInt(tv_scoreTeamA.getText().toString());
        tv_scoreTeamA.setText(String.valueOf(s + 1));
    }

    public void resetScore(View view){

        frag_teamA = (CourtCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_teamA);
        frag_teamB = (CourtCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_teamB);

        frag_teamA.resetScore();
        frag_teamB.resetScore();

    }

    private void display() {
//        tv_scoreTeamA.setText(String.valueOf(mScoreTeamA));
    }

    public void onClickForward(View view){

        int id = view.getId();

        switch (id){
            case R.id.btn_3pt:
                add3Points(view);
                break;
            case R.id.btn_2pt:
                add2Points(view);
                break;
            case R.id.btn_1pt:
                add1Point(view);
                break;
            case R.id.btn_reset:
                resetScore(view);
                break;
        }

    }
}
