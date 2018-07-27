package com.example.student.lab11_listview;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_IMAGE_ID = "com.example.student.imageId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initListView();
    }

    private void initListView() {
        ListView lv = findViewById(R.id.lv_pokemons);
        final MyListAdapter la = new MyListAdapter(this);
        lv.setAdapter(la);
        lv.setEmptyView(findViewById(R.id.empty));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TypedArray images = getResources().obtainTypedArray(R.array.pokemonImages);
                int imageId = la.getImages().getResourceId(position, 0);
//                images.recycle();

                Intent intent = new Intent();
                intent.putExtra(BUNDLE_KEY_IMAGE_ID, imageId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
