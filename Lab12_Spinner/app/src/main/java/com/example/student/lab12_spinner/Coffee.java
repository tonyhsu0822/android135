package com.example.student.lab12_spinner;

import java.io.Serializable;

public class Coffee implements Serializable{

    private static final long serialVersionUID = 1L;

// static coffee samples
//==================================================
// non-static

    private int mImageId;
    private String mName;
    private int mPrice;

    // constructor for coffee samples, with illegal price
    Coffee(int image, String name){
        mImageId = image;
        mName = name;
        mPrice = -1;
    }

    // construct with a coffee sample
    Coffee(Coffee coffee, int price) {
        mImageId = coffee.getImageId();
        mName = coffee.getName();
        mPrice = price;
    }

    public int getImageId() {
        return mImageId;
    }

    public String getName() {
        return mName;
    }

    public int getPrice(){
        return mPrice;
    }

    @Override
    public String toString(){
        return mName;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Coffee))
            return false;

        Coffee coffee = (Coffee) obj;
        return (mImageId == coffee.getImageId()) && (mName.equals( coffee.getName() ));
    }
}
