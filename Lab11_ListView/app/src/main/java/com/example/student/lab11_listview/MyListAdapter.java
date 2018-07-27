package com.example.student.lab11_listview;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MyListAdapter extends BaseAdapter {

    private TypedArray images;

    public TypedArray getImages() {
        return images;
    }

    private Activity mActivity;

    public MyListAdapter(Activity activity) {
        mActivity = activity;

        images = activity.getResources().obtainTypedArray(R.array.pokemonImages);
    }


    @Override
    public int getCount() {
        return images.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    } // 結合資料庫才會用到

    @Override
    public long getItemId(int position) {
        return 0;
    } // 結合資料庫才會用到

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mActivity.getLayoutInflater().inflate(R.layout.listview_layout, null);

        TextView tv = v.findViewById(R.id.item_id);
        tv.setText(String.valueOf(position+1));
        ImageView iv = v.findViewById(R.id.item_image);
        iv.setImageDrawable(images.getDrawable(position));

        return v;
    }
}
