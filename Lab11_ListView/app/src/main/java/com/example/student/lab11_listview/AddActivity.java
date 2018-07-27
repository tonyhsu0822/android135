package com.example.student.lab11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    public static final int PICK_IMAGE_REQUEST = 0;
//    public static final String BUNDLE_KEY_POKEMON_ID = "com.example.student.pokemon_id";
//    public static final String BUNDLE_KEY_POKEMON_NAME = "com.example.student.pokemon_name";
//    public static final String BUNDLE_KEY_POKEMON_IMAGE = "com.example.student.pokemon_image";
    public static final String BUNDLE_KEY_POKEMON = "com.example.student.pokemon";

    private static final String STATE_IMAGE_ID = "com.example.student.state_imageId";

    protected ImageButton mImageButton;
    protected EditText mEditTextId;
    protected EditText mEditTextName;
    protected int mImageId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        if(savedInstanceState != null){
            mImageId = savedInstanceState.getInt(STATE_IMAGE_ID);
            mImageButton.setImageResource(mImageId);
            Log.d("AddActivity.onCreate:", "mImageId = " + mImageId);
        }
        else{
            mImageButton.setImageResource(R.drawable.no_image_box);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_IMAGE_ID, mImageId);
        Log.d("AddActivity.onSave:", "put mImageId = " + mImageId);

        super.onSaveInstanceState(outState);
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        mImageId = savedInstanceState.getInt(STATE_IMAGE_ID);
//    }

    private void initView(){
        mImageButton = findViewById(R.id.ib_image);
        mEditTextId = findViewById(R.id.et_id);
        mEditTextName = findViewById(R.id.et_name);

        mImageId = R.drawable.no_image_box;
    }

    public void pickImage(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();

                mImageId = bundle.getInt(ListActivity.BUNDLE_KEY_IMAGE_ID, 0);
                if(mImageId != 0)
                    mImageButton.setImageResource(mImageId);
            }
        }
    }

    public void add_ok(View view) {
        String id = mEditTextId.getText().toString();
        String name = mEditTextName.getText().toString();

        int colorRed = getResources().getColor(R.color.red);
        boolean missingElement = false;

        if(id.equals("")){
            mEditTextId.setHintTextColor(colorRed);
            missingElement = true;
        }
        if(name.equals("")){
            mEditTextName.setHintTextColor(colorRed);
            missingElement = true;
        }
        if(mImageId == 0){
            mImageButton.setBackgroundColor(colorRed);
            missingElement = true;
        }
        if(!missingElement){
            Pokemon pm = new Pokemon(id, name, mImageId);
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_KEY_POKEMON, pm);
//            intent.putExtra(BUNDLE_KEY_POKEMON_ID, id)
//                    .putExtra(BUNDLE_KEY_POKEMON_NAME, name)
//                    .putExtra(BUNDLE_KEY_POKEMON_IMAGE, mImageId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void add_cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
