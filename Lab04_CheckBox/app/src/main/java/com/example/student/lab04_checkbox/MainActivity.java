package com.example.student.lab04_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_quantity;
    private TextView tv_summary;
    private CheckBox cb_kimuchi;
    private int mQuantity = 0;
    private int mPrice = 50;

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllView();
    }

    private void findAllView() {
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_summary = findViewById(R.id.tv_summary);
        cb_kimuchi = findViewById(R.id.cb_kimuchi);
    }


    public void increment() {
        displayQuantity(++mQuantity);
        clearSummary();
    }

    public void decrement() {
        if(mQuantity > 0) {
            displayQuantity(--mQuantity);
            clearSummary();
        }
    }

    public void displayQuantity(int q){
        tv_quantity.setText(String.valueOf(q));
    }

    public void clearSummary(){
        sb.setLength(0);
        tv_summary.setText(sb);
    }

    public void submitOrder() {
        boolean kimuchi = cb_kimuchi.isChecked();
        int totalPrice = mPrice * mQuantity;

        sb.append("Quantity: ")
                .append(mQuantity)
                .append("\n");
        if(kimuchi)
            sb.append("with topping\n");
        else
            sb.append("without topping\n");
        sb.append("total: NT$ ")
                .append(totalPrice);

        tv_summary.setText(sb);

    }

    public void onClickForward(View view){

        clearSummary();

        int id = view.getId();
        switch(id) {
            case R.id.btn_dec:
                decrement();
                break;
            case R.id.btn_inc:
                increment();
                break;
            case R.id.btn_order:
                submitOrder();
                break;
        }
    }
}




