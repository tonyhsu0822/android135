package com.example.student.lab12_spinner;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySpinnerAdapter extends BaseAdapter {

    private Activity mActivity;
    private Coffee[] mCoffeeSamples;
//    private TypedArray mCoffeeImages;
//    private String[] mCoffeeNames;

    public MySpinnerAdapter(Activity activity){
        mActivity = activity;
        mCoffeeSamples = Coffee.getCoffeeSamples();

//        Resources resources = activity.getResources();
//        mCoffeeImages = resources.obtainTypedArray(R.array.coffee_drawables);
//        mCoffeeNames = resources.getStringArray(R.array.coffee_names);
    }

    @Override
    public int getCount() {
        Log.d("getCount()", String.valueOf(mCoffeeSamples.length));
        return mCoffeeSamples.length;
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
        //
//        if(i == (getCount() - 1)){
//            view.setVisibility(View.INVISIBLE);
//            return view;
//        }

        Coffee coffee;
        if(i >= 0 && i < getCount()) {
             coffee = mCoffeeSamples[i];
        } else {
            coffee = Coffee.getCoffeeSampleOf(0);
        }
        TextView tv = view.findViewById(R.id.tv_coffee_name);
        tv.setText(coffee.getName());
        ImageView iv = view.findViewById(R.id.iv_coffee_image);
        iv.setImageResource(coffee.getImageId());

        return view;
    }
}
