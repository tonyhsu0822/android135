package com.example.student.lab12_spinner;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySpinnerAdapter extends BaseAdapter {

    private Activity mActivity;
    private TypedArray mCoffeeImages;
    private String[] mCoffeeNames;

    public MySpinnerAdapter(Activity activity){
        mActivity = activity;
        mCoffeeImages = activity.getResources().obtainTypedArray(R.array.coffee_drawables);
        mCoffeeNames = activity.getResources().getStringArray(R.array.coffee_names);
    }

    @Override
    public int getCount() {
        return mCoffeeImages.length();
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
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.layout_spinner, null);

        TextView tv = view.findViewById(R.id.tv_coffee_name);
        tv.setText(mCoffeeNames[i]);
        ImageView iv = view.findViewById(R.id.iv_coffee_image);
        iv.setImageDrawable(mCoffeeImages.getDrawable(i));

        return view;
    }
}
