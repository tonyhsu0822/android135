package com.example.student.lab12_spinner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
                implements MyDialogFragment.CoffeeHandler {

    public static final String BUNDLE_KEY_SELECTED_COFFEE = "com.example.student.selected_coffee";
    public static final String TAG = "MainActivity";
    private static final String DATA_FILENAME = "coffee_list.data";

    private MyListAdapter mAdapter;
    private ArrayList<Coffee> mCoffeeList;

    private int mSelectedIndex;

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
                new MyDialogFragment().show(getSupportFragmentManager(), TAG);
            }
        });

        if(mCoffeeList == null && !loadData()){
            mCoffeeList = new ArrayList<>();
        }
        initListView();
    }

    private void initListView(){
        ListView listView = findViewById(R.id.main_list_view);
        mAdapter = new MyListAdapter(this, mCoffeeList);
        listView.setAdapter(mAdapter);
        listView.setEmptyView(findViewById(R.id.tv_empty));
        listView.setOnItemClickListener(this.new OnItemClickedListener());
    }

    class OnItemClickedListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
            mSelectedIndex = i;

            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("do what?")
                    .setPositiveButton("modify", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(
                                    BUNDLE_KEY_SELECTED_COFFEE, mCoffeeList.get(mSelectedIndex));
                            MyDialogFragment fragment = new MyDialogFragment();
                            // pass the selected coffee to fragment
                            fragment.setArguments(bundle);
                            // set dialog type to modify
                            fragment.setDialogType(MyDialogFragment.DialogType.MODIFY);
                            fragment.show(getSupportFragmentManager(), TAG);
                        }
                    })
                    .setNegativeButton("cancel", null)
                    .setNeutralButton("delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // delete coffee
                            mCoffeeList.remove(mSelectedIndex);
                            mAdapter.notifyDataSetChanged();
                        }
                    })
                    .show();
        }
    }

    /* CoffeeHandler interface implementation below */
    @Override
    public void addCoffee(Coffee coffee) {
        mCoffeeList.add(coffee);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void modifyCoffee(Coffee coffee){
        mCoffeeList.set(mSelectedIndex, coffee);
        mAdapter.notifyDataSetChanged();
    }
    /* CoffeeHandler interface implementation above */

    /* data S/L below */
    @Override
    protected void onStop() {
        saveData();
        super.onStop();
    }

    private void saveData() {
        Log.d(TAG, "save data");
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    openFileOutput(DATA_FILENAME, MODE_PRIVATE));
            os.writeObject(mCoffeeList);
            os.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // return false if exception was thrown
    // return true if everything goes well
    private boolean loadData(){
        Log.d(TAG, "load data");
        try {
            ObjectInputStream is = new ObjectInputStream(
                    openFileInput(DATA_FILENAME));
            mCoffeeList = (ArrayList<Coffee>)is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /* data S/L above */

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
