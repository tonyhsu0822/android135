package com.example.student.lab12_spinner;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    public interface CoffeeHandler {
        void addCoffee(Coffee coffee);
        void modifyCoffee(Coffee coffee);
    }

    private Activity mActivity;
    private CoffeeHandler coffeeHandler;

    private Spinner spinner;
    private EditText et_price;
    private MySpinnerAdapter mAdapter;

    private static final String TAG = "MyDialogFragment";
    private int mSelectedIndex;

    public enum DialogType {
        ADD, MODIFY
    }

    private DialogType mDialogType = DialogType.ADD;

    public void setDialogType(DialogType type){
        mDialogType = type;
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mActivity = getActivity();
        View view = mActivity.getLayoutInflater().inflate(R.layout.fragment_my_dialog, null);

        initView(view);
        initCoffeeHandler();
        initSpinner();
        if(mDialogType == DialogType.MODIFY) {
            coffeeDataFillBack();
        }

        return buildDialog(mActivity, view);
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        et_price = view.findViewById(R.id.et_price);
    }

    private void initCoffeeHandler() {
        if(mActivity instanceof CoffeeHandler) {
            coffeeHandler = (CoffeeHandler) mActivity;
        } else {
            throw new RuntimeException(
                    "MyDialogFragment: parent activity didn't implement CoffeeHandler");
        }
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
                // do nothing
            }
        });
    }

    private void coffeeDataFillBack() {
        Bundle bundle;
        if((bundle = getArguments()) == null) return;

        Coffee coffee = (Coffee)bundle.getSerializable(
                   MainActivity.BUNDLE_KEY_SELECTED_COFFEE);
        et_price.setText(String.valueOf(coffee.getPrice()));

        int index = Coffees.getIndexByCoffee(coffee);
        if(index != -1) {
            spinner.setSelection(index);
        }
    }

    private Dialog buildDialog(final Activity activity, View view) {
        int iconId;
        String title;
        switch(mDialogType) {
            case ADD:
                title = "Add coffee";
                iconId = android.R.drawable.ic_input_add;
                break;
            case MODIFY:
                title = "Modify coffee";
                iconId = android.R.drawable.ic_menu_edit;
                break;
            default:
                title = "Add coffee";
                iconId = android.R.drawable.ic_input_add;
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view)
                .setTitle(title)
                .setIcon(iconId)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Coffee coffee = createCoffeeItem();
                            switch(mDialogType) {
                                case ADD:
                                    coffeeHandler.addCoffee(coffee);
                                    break;
                                case MODIFY:
                                    coffeeHandler.modifyCoffee(coffee);
                                    break;
                                default:
                                    coffeeHandler.addCoffee(coffee);
                                    break;
                            }

                        } catch (NumberFormatException e) {
                            Toast.makeText(mActivity,
                                    "Illegal or empty price", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialogSetting(dialog);

        return dialog;
    }

    private Coffee createCoffeeItem(){
        // get content of EditText
        String strPrice = et_price.getText().toString();
        int price = Integer.parseInt(strPrice);
        return new Coffee( Coffees.getCoffeeSampleOf(mSelectedIndex) , price);
    }

    private void dialogSetting(final AlertDialog dialog) {

        // set the "OK" button disabled
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                Button posButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                if(et_price.getText().length() > 0) {
                    // enable the "OK" button when EditText isn't empty
                    posButton.setEnabled(true);
                } else {
                    posButton.setEnabled(false);
//                    et_price.setError("please enter the price");
                }
            }
        });

        // set EditText changed listener
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                Button posButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                if(cs.length() > 0) {
                    // enable the "OK" button when EditText isn't empty
                    posButton.setEnabled(true);
                } else {
                    posButton.setEnabled(false);
                    et_price.setError("please enter the price");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
    }
}
