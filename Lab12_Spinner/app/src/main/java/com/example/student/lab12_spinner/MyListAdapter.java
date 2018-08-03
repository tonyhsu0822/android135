package com.example.student.lab12_spinner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    private ArrayList<Coffee> mCoffeeList;
    private Activity mActivity;

    public MyListAdapter(Activity activity, ArrayList<Coffee> coffeeList){
        mActivity = activity;
        mCoffeeList = coffeeList;
    }

    @Override
    public int getCount() {
        return mCoffeeList.size();
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
        View v = mActivity.getLayoutInflater()
                            .inflate(R.layout.layout_main_listview, null);

        Coffee coffee = mCoffeeList.get(i);

        TextView tv_name = v.findViewById(R.id.coffee_name);
        tv_name.setText(coffee.getName());
        ImageView iv = v.findViewById(R.id.coffee_image);
        iv.setImageResource(coffee.getImageId());
        TextView tv_price = v.findViewById(R.id.coffee_price);
        String priceNotation = "$" + String.valueOf(coffee.getPrice());
        tv_price.setText(priceNotation);

        return v;
    }
}
