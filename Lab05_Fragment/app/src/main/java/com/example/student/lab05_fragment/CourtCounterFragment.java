package com.example.student.lab05_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourtCounterFragment extends Fragment implements View.OnClickListener{

    private TextView et_teamName;
    private TextView tv_score;
    private Button btn_3pt;
    private Button btn_2pt;
    private Button btn_1pt;
    private ImageView img_teamLogo;

    private int mScore = 0;


    public CourtCounterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_court_counter, container, false);
    }


    @Override
    public void onStart(){
        super.onStart();

        et_teamName = getView().findViewById(R.id.et_teamName);
        tv_score = getView().findViewById(R.id.tv_score);
        btn_3pt = getView().findViewById(R.id.btn_3pt);
        btn_2pt = getView().findViewById(R.id.btn_2pt);
        btn_1pt = getView().findViewById(R.id.btn_1pt);
        img_teamLogo = getView().findViewById(R.id.img_teamLogo);

        btn_3pt.setOnClickListener(this);
        btn_2pt.setOnClickListener(this);
        btn_1pt.setOnClickListener(this);
    }

    public TextView getEt_teamName() {
        return et_teamName;
    }

    public TextView getTv_score() {
        return tv_score;
    }

    public void setTeamName(String teamName){
        et_teamName.setText(teamName);
    }

    public void setTeamLogo(int res_id){
        if(img_teamLogo != null){
            img_teamLogo.setImageResource(res_id);
        }
    }

    @Override
    public void onClick(View view) {

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
        }

    }

    public void add3Points(View view){

        mScore += 3;
        display();
    }


    public void add2Points(View view){

        mScore += 2;
        display();
    }

    public void add1Point(View view){

        mScore ++;
        display();
    }

    private void display(){

        tv_score.setText(String.valueOf(mScore));
    }

    public void resetScore(){
        mScore = 0;
        display();
    }




}
