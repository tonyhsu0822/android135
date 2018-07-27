package com.example.student.lab02_interactive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_amount;
    private TextView textView_price;
    private TextView textView_free;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_amount = findViewById(R.id.textView_amount);
        textView_price = findViewById(R.id.textView_price);
        textView_free = findViewById(R.id.textView_free);
    }

    public void submitOrder(View view) {
        int amount = getAmount();
        displayPrice(amount);
    }

    public void increaseAmount(View view) {
        int amount = getAmount();
        amount = amount + 1;
        displayAmount(amount);
    }

    public void decreaseAmount(View view) {
        int amount = getAmount();
        if(amount > 0) {
            amount = amount - 1;
            displayAmount(amount);
        }
    }

    private void displayPrice(int amount){

        textView_price.setText("NT$ " + String.valueOf(amount*price));
        if(amount > 0){
            textView_free.setText("Thank you!");
        }
        else{
            textView_free.setText("Free");
        }

    }

    private void displayAmount(int amount) {
        textView_amount.setText(String.valueOf(amount));
    }

    private int getAmount() {
        String s = textView_amount.getText().toString();
        return Integer.parseInt(s);
    }
}
