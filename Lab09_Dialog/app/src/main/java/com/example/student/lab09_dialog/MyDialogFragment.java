package com.example.student.lab09_dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    private EditText m_et_username;

    interface MyDialogFragmentInteraction {
        void signIn(String username);
        void cancel();
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_dialog, container, false);
//    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_my_dialog, null);

        m_et_username = view.findViewById(R.id.et_username);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = m_et_username.getText().toString();
//                        TextView tv = ((MainActivity)getActivity()).getTv_message();
//                        tv.setText("Welcome " + username + "!");

                        Activity activity = getActivity();
                        if(activity instanceof MyDialogFragmentInteraction){
                            MyDialogFragmentInteraction interaction = (MyDialogFragmentInteraction) activity;
                            interaction.signIn(username);
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Activity activity = getActivity();
                        if(activity instanceof MyDialogFragmentInteraction){
                            MyDialogFragmentInteraction interaction = (MyDialogFragmentInteraction) activity;
                            interaction.cancel();
                        }
                        else{
                            throw new RuntimeException("the activity of this fragment didn't implements" +
                                                        "the \"MyDialogFragmentInteraction\" interface ");
                        }
                    }
                });

        return builder.create();

    }
}
