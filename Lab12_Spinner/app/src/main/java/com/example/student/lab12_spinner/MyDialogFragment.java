package com.example.student.lab12_spinner;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    private Spinner spinner;
    private EditText et_price;
    private MySpinnerAdapter mAdapter;

    private static final String TAG = "MyDialogFragment";
    private int mSelectedIndex;
//    private Coffee mSelectedCoffeeSample;

    public MyDialogFragment() {
        // Required empty public constructor
    }

    interface CoffeeInterface {
        void addCoffee(Coffee coffee);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Activity activity = getActivity();
        View view = activity.getLayoutInflater().inflate(R.layout.fragment_my_dialog, null);

        initView(view);
        initSpinner();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("Add item")
                .setIcon(android.R.drawable.ic_input_add)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strPrice = et_price.getText().toString();
                        try {
                            int price = Integer.parseInt(strPrice);
                            if(activity instanceof CoffeeInterface){
//                                Log.d(TAG, mSelectedCoffeeSample.toString());
                                Coffee coffee = new Coffee(Coffee.getCoffeeSampleOf(mSelectedIndex), price);
                                ((CoffeeInterface) activity).addCoffee(coffee);
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(activity,
                                    "Illegal or empty price", Toast.LENGTH_LONG).show();

                            // dialog close after button clicked, so these are not effected
//                            et_price.setHint("Illegal price");
//                            et_price.setBackgroundColor(
//                                              getResources().getColor(R.color.red));
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        et_price = view.findViewById(R.id.et_price);
    }

    private void initSpinner() {
        mAdapter = new MySpinnerAdapter(getActivity());
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "select" + position);
                mSelectedIndex = position;
//                mSelectedCoffeeSample = Coffee.getCoffeeSampleOf(position);
            }

            // if the spinner has no item
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Log.d(TAG, "nothing selected");
//                mSelectedIndex = 0;
//                mSelectedCoffeeSample = Coffee.getCoffeeSampleOf(0);
            }
        });

        Bundle bundle;
        if((bundle = getArguments()) != null){
            Coffee coffee = (Coffee)bundle.getSerializable(
                                        MainActivity.BUNDLE_KEY_SELECTED_COFFEE);
            et_price.setText(String.valueOf(coffee.getPrice()));
            // TODO set selection of spinner
        }
    }
}
