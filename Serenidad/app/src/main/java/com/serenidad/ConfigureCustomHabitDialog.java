package com.serenidad;

/**
 * Created by Michael Eierman on 11/2/2016.
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ConfigureCustomHabitDialog extends DialogFragment {                           //1


    public ConfigureCustomHabitDialog() {                                                  //3
        // Empty constructor required for DialogFragment
    }

    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.configure_custom_habit_dialog,container);
        Button next = (Button) view.findViewById(R.id.buttonNext);
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextHabitName = (EditText)view.findViewById(R.id.editCustomHabitName);
                EditText editTextHabitScale = (EditText)view.findViewById(R.id.editCustomHabitScale);
                String editTextHabitNameStr = editTextHabitName.getText().toString();
                String editTextHabitScaleStr = editTextHabitScale.getText().toString();
                if(!editTextHabitNameStr.equals("") && !editTextHabitScaleStr.equals("")){

                    FragmentManager fm = getFragmentManager();
                    ConfigureHabitDialog configureHabitDialog = new ConfigureHabitDialog();
                    Bundle args = new Bundle();
                    args.putString("habitName",editTextHabitNameStr);
                    args.putString("habitScale",editTextHabitScaleStr);
                    args.putBoolean("addCustomHabit",true);
                    configureHabitDialog.setArguments(args);
                    getDialog().dismiss();
                    configureHabitDialog.show(fm, "ConfigureHabitDialog");
                }
            }
        });

        Button cancelBtn = (Button)view.findViewById(R.id.buttonCancel);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Intent i = new Intent(getActivity(), HealthMainActivity.class);
                getActivity().startActivity(i);
            }
        });
        return view;
    }


}
