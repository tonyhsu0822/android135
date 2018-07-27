package com.example.student.lab10_animationdrawable;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView m_iv_duke;
    private TextView m_tv_count_down;
    private TextView m_tv_lastTime;
    private AnimationDrawable m_animationDrawable;
//    private View m_view_team_logo1;
    private View m_view_team_logo2;
    private TextView m_tv_team_name;
    private Button m_btn_go;

    private int mLastTime;
    private int mTeamLogoCount;
    private TypedArray mTeamLogos;
    private String[] mTeamNames;

    private Animation anim_in;
    private Animation anim_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFrameAnimation();
        initNbaLogos();
        initAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTeamLogos.recycle();
    }

    private void initView() {
        m_iv_duke = findViewById(R.id.iv_duke);
        m_tv_count_down = findViewById(R.id.tv_count_down);
        m_tv_lastTime = findViewById(R.id.tv_last_time);
//        m_view_team_logo1 = findViewById(R.id.view_team_logo1);
        m_view_team_logo2 = findViewById(R.id.view_team_logo2);
        m_tv_team_name = findViewById(R.id.tv_team_name);
        m_btn_go = findViewById(R.id.btn_go);

        // init timer
        mLastTime = 5;
        m_tv_lastTime.setText(String.valueOf(mLastTime));
    }

    private void initFrameAnimation(){
        m_iv_duke.setBackgroundResource(R.drawable.frame_animation);
        m_animationDrawable = (AnimationDrawable) m_iv_duke.getBackground();
    }

    private void initNbaLogos(){
        mTeamLogos = getResources().obtainTypedArray(R.array.nba_logos);
        mTeamLogoCount = mTeamLogos.length();
        m_view_team_logo2.setBackground(mTeamLogos.getDrawable(0));
//        m_view_team_logo2.setBackgroundResource(R.drawable.nba_logo_76ers);

        mTeamNames = getResources().getStringArray(R.array.nba_team_names);
        m_tv_team_name.setText(mTeamNames[0]);
    }

    private void initAnimation(){
        anim_in = AnimationUtils.loadAnimation(this, R.anim.in_from_top);
        anim_out = AnimationUtils.loadAnimation(this, R.anim.out_to_bottom);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_start:
                m_animationDrawable.start();
                break;
            case R.id.btn_stop:
                m_animationDrawable.stop();
                break;
            case R.id.btn_5sec:
                runAnimation(mLastTime);
                break;
            case R.id.btn_inc_time:
                setTimer(1);
                break;
            case R.id.btn_dec_time:
                setTimer(-1);
                break;
            case R.id.btn_go:
                randomLogos();
                break;
            case R.id.btn_test:
                animationTest();
                break;
        }
    }

    private void animationTest() {
        anim_out.reset();
        anim_in.reset();

//        m_view_team_logo1.setBackground(mTeamLogos.getDrawable(1));
//        m_view_team_logo1.clearAnimation();
//        m_view_team_logo1.startAnimation(anim_in);

        m_view_team_logo2.clearAnimation();
        m_view_team_logo2.startAnimation(anim_out);
    }

    private void randomLogos() {

        final Runnable startRandom = new Runnable(){
            @Override
            public void run(){
                int index = (int)(Math.random() * mTeamLogoCount);
                m_view_team_logo2.setBackground(mTeamLogos.getDrawable(index));
                m_tv_team_name.setText(mTeamNames[index]);
                m_handler.postDelayed(this, 100);
            }
        };

        Runnable stopRandom = new Runnable(){
            @Override
            public void run(){
                m_handler.removeCallbacks(startRandom);
                m_btn_go.setEnabled(true);
            }
        };

        m_handler.post(startRandom);
        m_handler.postDelayed(stopRandom, 3000);
        m_btn_go.setEnabled(false);
    }

    private void setTimer(int t) {
        mLastTime += t;
        m_tv_lastTime.setText(String.valueOf(mLastTime));
    }

    private Handler m_handler = new Handler();

    private void runAnimation(int last) {

//        Runnable toStop = new Runnable(){
//            @Override
//            public void run(){
//                m_animationDrawable.stop();
//                m_tv_count_down.setText("Time's up");
//            }
//        };

        Runnable countDown = new CountDown(last);

//        m_handler.postDelayed(toStop, last * 1000);
        m_handler.post(countDown);
        m_animationDrawable.start();

    }

    private class CountDown implements Runnable{

        int countDown;

        CountDown(int t){
            countDown = t;
        }

        @Override
        public void run() {
            if(countDown > 0) {
                m_tv_count_down.setText(String.valueOf(countDown));
                countDown--;
                m_handler.postDelayed(this, 1000);
            }
            else{
                m_animationDrawable.stop();
                m_tv_count_down.setText("Time's up");
            }
        }
    }

}
