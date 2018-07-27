package com.example.student.lab11_listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MainListAdapter extends BaseAdapter {

    private ArrayList<Pokemon> pokemons;
    private Activity mActivity;

    public MainListAdapter(Activity activity, ArrayList<Pokemon> arrayList){
        mActivity = activity;
        pokemons = arrayList;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mActivity.getLayoutInflater()
                            .inflate(R.layout.listview_for_main_layout, null);

        Pokemon pm = pokemons.get(position);

        TextView tv_id = v.findViewById(R.id.pokemon_id);
        tv_id.setText(pm.getId());
        TextView tv_name = v.findViewById(R.id.pokemon_name);
        tv_name.setText(pm.getName());
        ImageView iv_image = v.findViewById(R.id.pokemon_image);
        iv_image.setImageResource(pm.getImageId());

        return v;
    }
}
