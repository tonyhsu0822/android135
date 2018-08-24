package com.example.student.lab13_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvAttractions;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRvAttractions = findViewById(R.id.recyclerView);
        // increase efficiency if every item has the same size
        mRvAttractions.setHasFixedSize(true);

        // set orientation of recycler view
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvAttractions.setLayoutManager(manager);

        mAdapter = new MyRecyclerViewAdapter(this);
        mRvAttractions.setAdapter(mAdapter);
    }
}
