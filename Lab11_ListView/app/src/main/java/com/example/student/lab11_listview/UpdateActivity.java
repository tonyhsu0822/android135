package com.example.student.lab11_listview;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class UpdateActivity extends AddActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadPokemonData();
    }

    private void loadPokemonData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Serializable ser = bundle.getSerializable(BUNDLE_KEY_POKEMON);
        if(ser != null && ser instanceof Pokemon) {
            Pokemon pm = (Pokemon)ser;
            mEditTextId.setText(pm.getId());
            mEditTextName.setText(pm.getName());
            mImageId = pm.getImageId();
            mImageButton.setImageResource(mImageId);
        }

//            String id = bundle.getString(BUNDLE_KEY_POKEMON_ID);
//            String name = bundle.getString(BUNDLE_KEY_POKEMON_NAME);
//            int imageId = bundle.getInt(BUNDLE_KEY_POKEMON_IMAGE);
//            mEditTextId.setText(id);
//            mEditTextName.setText(name);
//            mImageButton.setImageResource(imageId);
    }
}
