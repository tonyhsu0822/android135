package com.example.student.lab11_listview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_POKEMON_REQUEST = 1;
    public static final int UPDATE_POKEMON_REQUEST = 2;
    private static final String STATE_PMLIST = "com.example.student.pokemonList";
    private static final String STATE_CURRENT_MODIFIED_INDEX = "com.example.student.currentModifiedIndex";
    private static final String TAG = "MainActivity";
    private static final String FILENAME = "pokemon-list.data";

    private ArrayList<Pokemon> pokemonList;
    private MainListAdapter mAdapter;

    private int mCurrentModifiedIndex;

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
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_POKEMON_REQUEST);
            }
        });

        // 畫面旋轉時讀取資料
        if(savedInstanceState != null){
            Serializable ser = savedInstanceState.getSerializable(STATE_PMLIST);
            if(ser != null && ser instanceof ArrayList){
//                Log.d("onRestoreInstanceState", "got saved list");
                pokemonList = (ArrayList<Pokemon>)ser;
            }
        }

        // 重新開啟app時讀取資料
        if(!loadData()){
            pokemonList = new ArrayList<>();
        }

        initListView();
    }

    private void initListView(){
        ListView lv = findViewById(R.id.lv_my_pokemons);
        mAdapter = new MainListAdapter(this, pokemonList);
        lv.setAdapter(mAdapter);
        lv.setEmptyView(findViewById(R.id.tv_empty));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("要做什麼?")
                        .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCurrentModifiedIndex = position;

                                Pokemon pm = pokemonList.get(position);
                                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                                intent.putExtra(AddActivity.BUNDLE_KEY_POKEMON, pm);
//                                intent.putExtra(AddActivity.BUNDLE_KEY_POKEMON_ID, pm.getId())
//                                        .putExtra(AddActivity.BUNDLE_KEY_POKEMON_NAME, pm.getName())
//                                        .putExtra(AddActivity.BUNDLE_KEY_POKEMON_IMAGE, pm.getImageId())
                                startActivityForResult(intent, UPDATE_POKEMON_REQUEST);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // nothing to do
                            }
                        })
                        .setNeutralButton("刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pokemonList.remove(position);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
        });
    }

    // 離開app時儲存資料
    @Override
    protected void onStop() {
        saveData();
        super.onStop();
    }

    private void saveData(){
        try{
            // [openFileOutput()] inherit from class [Context], superclass of [Activity]
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(pokemonList);

            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // return true if everything goes right
    // return false if file not found or other io exception was thrown
    private boolean loadData(){
        try{
            // [openFileInput()] inherit from class [Context]
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            pokemonList = (ArrayList)ois.readObject();

            fis.close();
            ois.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 畫面旋轉時儲存資料
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(STATE_PMLIST, pokemonList);
        outState.putInt(STATE_CURRENT_MODIFIED_INDEX, mCurrentModifiedIndex);

        super.onSaveInstanceState(outState);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_POKEMON_REQUEST){
            if(resultCode == RESULT_OK){
//                String id = bundle.getString(AddActivity.BUNDLE_KEY_POKEMON_ID);
//                String name = bundle.getString(AddActivity.BUNDLE_KEY_POKEMON_NAME);
//                int imageId = bundle.getInt(AddActivity.BUNDLE_KEY_POKEMON_IMAGE);
                Serializable ser = data.getSerializableExtra(AddActivity.BUNDLE_KEY_POKEMON);
                if(ser != null && ser instanceof Pokemon) {
                    Pokemon pm = (Pokemon) ser;
                    pokemonList.add(pm);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
        else if(requestCode == UPDATE_POKEMON_REQUEST){
            if(resultCode == RESULT_OK){

                Serializable ser = data.getSerializableExtra(AddActivity.BUNDLE_KEY_POKEMON);
                if(ser != null && ser instanceof Pokemon) {
                    Pokemon pm = (Pokemon) ser;
                    pokemonList.set(mCurrentModifiedIndex, pm);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

//    @Override
//    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
//        intent.putExtra(BUNDLE_KEY_REQUEST_CODE, requestCode);
//        super.startActivityForResult(intent, requestCode, options);
//    }
}
