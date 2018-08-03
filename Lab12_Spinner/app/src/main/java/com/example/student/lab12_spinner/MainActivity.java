package com.example.student.lab12_spinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
                implements MyDialogFragment.CoffeeInterface {

//    private ListView mCoffeeList;
    private MyListAdapter mAdapter;
    private ArrayList<Coffee> mCoffeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyDialogFragment().show(getSupportFragmentManager(), "tag");
            }
        });

        mCoffeeList = new ArrayList<>();
//        initView();
        initListView();
    }

//    private void initView(){
//
//    }

    private void initListView(){
        ListView listView = findViewById(R.id.main_list_view);
        mAdapter = new MyListAdapter(this, mCoffeeList);
        listView.setAdapter(mAdapter);
        listView.setEmptyView(findViewById(R.id.tv_empty));
        // TODO setOnItemClickListener
    }

    @Override
    public void addCoffee(Coffee coffee) {
        mCoffeeList.add(coffee);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
