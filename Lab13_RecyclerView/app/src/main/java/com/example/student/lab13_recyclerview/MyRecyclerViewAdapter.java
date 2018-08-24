package com.example.student.lab13_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    Activity mActivty;

    public MyRecyclerViewAdapter(Activity activity) {
        mActivty = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext())
//                        .inflate(R.layout.item_attraction, viewGroup, false);
        View view = mActivty.getLayoutInflater()
                        .inflate(R.layout.item_attraction, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
//        Log.d("MyRecyclerViewAdapter", "onBindViewHolder: " + i);
        holder.tv_stitle.setText("number " + i);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_stitle;
        private TextView tv_category;
        private ImageView iv_image;

        private static int constructCount = 0;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_stitle = itemView.findViewById(R.id.tv_stitle);
            tv_category = itemView.findViewById(R.id.tv_category);
            iv_image = itemView.findViewById(R.id.iv_image);

//            Log.d("MyViewHolder", "construct: " + constructCount++);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // getAdapterPosition() inherited from RecyclerView.ViewHolder
                    int pos = getAdapterPosition();
                    Log.d("MyViewHolder", "click " + pos);
                }
            });
        }
    }
}
