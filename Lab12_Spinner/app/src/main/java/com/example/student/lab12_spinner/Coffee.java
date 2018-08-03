package com.example.student.lab12_spinner;

public class Coffee {

    private static int[] coffeeImages = {
            R.drawable.coffee_cappuccino,
            R.drawable.coffee_latte,
            R.drawable.coffee_macchiato,
            R.drawable.coffee_mocha
    };

    private static String[] coffeeNames = {
            "cappuccino",
            "latte",
            "macchiato",
            "mocha"
    };

    private static Coffee[] coffeeSamples = new Coffee[4];

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
