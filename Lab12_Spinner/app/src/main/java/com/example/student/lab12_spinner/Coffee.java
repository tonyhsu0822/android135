package com.example.student.lab12_spinner;

import java.io.Serializable;

public class Coffee implements Serializable{

    // to add new coffee sample, add new item to both array respectively,
    // and modify the size of [coffeeSamples] array

    private static int[] coffeeImages = {
            R.drawable.coffee_cappuccino,
            R.drawable.coffee_latte,
            R.drawable.coffee_macchiato,
            R.drawable.coffee_mocha,
            R.drawable.empty
    };

    private static String[] coffeeNames = {
            "cappuccino",
            "latte",
            "macchiato",
            "mocha",
            "no selection"
    };

    private static Coffee[] coffeeSamples = new Coffee[5];

    static {
        for(int i = 0; i < coffeeImages.length; i++){
            coffeeSamples[i] = new Coffee(coffeeImages[i], coffeeNames[i]);
        }
    }

    public static Coffee[] getCoffeeSamples(){
        return coffeeSamples;
    }

    public static Coffee getCoffeeSampleOf(int i) {
        return coffeeSamples[i];
    }
// static coffee samples
//==================================================
// non-static

    private int mImageId;
    private String mName;
    private int mPrice;

    // constructor for coffee samples, with illegal price
    private Coffee(int image, String name){
        mImageId = image;
        mName = name;
        mPrice = -1;
    }

    // construct with a coffee sample
    Coffee(Coffee coffee, int price){
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
}
