package com.example.student.lab12_spinner;

/* an util class for class "Coffee" */
public class Coffees {

    // to add new coffee sample, add new item to both array respectively,
    // and modify the size of [coffeeSamples] array

    private static int[] coffeeImages = {
            R.drawable.coffee_cappuccino,
            R.drawable.coffee_latte,
            R.drawable.coffee_macchiato,
            R.drawable.coffee_mocha,
    };

    private static String[] coffeeNames = {
            "cappuccino",
            "latte",
            "macchiato",
            "mocha",
    };

    private static Coffee[] coffeeSamples = new Coffee[4];

    static {
        for(int i = 0; i < coffeeSamples.length; i++){
            coffeeSamples[i] = new Coffee(coffeeImages[i], coffeeNames[i]);
        }
    }

    public static Coffee[] getCoffeeSamples(){
        return coffeeSamples;
    }

    public static Coffee getCoffeeSampleOf(int i) {
        return coffeeSamples[i];
    }

    public static int getIndexByCoffee(Coffee coffee) {
        for(int i = 0; i < coffeeSamples.length; i++) {
            if(coffeeSamples[i].equals(coffee))
                return i;
        }
        return -1;
    }
}
