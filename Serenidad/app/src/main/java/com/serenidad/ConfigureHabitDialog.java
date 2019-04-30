package com.serenidad;

/**
 * Created by Michael Eierman on 11/2/2016.
 */

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class ConfigureHabitDialog extends DialogFragment {                           //1


    public ConfigureHabitDialog() {                                                  //3
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

        final Habit selectedHabit=new Habit((Habit)getArguments().getSerializable("habit"));
        boolean isFromEditButton = false;
        final boolean addCustomHabit = getArguments().getBoolean("addCustomHabit",false);
        String customHabitName="";
        String customHabitScale="";
        final View view = inflater.inflate(R.layout.configure_habit,container);

        Button deleteButton = (Button)view.findViewById(R.id.buttonDelete);

        if (getArguments() != null) {

            isFromEditButton = getArguments().getBoolean("fromEditButton");
            //addCustomHabit = getArguments().getBoolean("addCustomHabit");
            if(isFromEditButton){
                deleteButton.setEnabled(true);
                deleteButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor( R.color.colorAccent)));
            }
            if(addCustomHabit){
                selectedHabit.setIsCustom(1);

                customHabitName = getArguments().getString("habitName");
                customHabitScale = getArguments().getString("habitScale");

                selectedHabit.setHabitname(customHabitName);
                selectedHabit.setScale(customHabitScale);
                selectedHabit.setHabiticon("plus");

            }

        }else{
            getDialog().dismiss();
        }

        final int selectedHabitId=(selectedHabit!=null)?selectedHabit.getHabitid():0;

        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource ds = new DataSource(getContext());
                try {
                    ds.open();
                    boolean updated = ds.deleteUserHabit(selectedHabitId);
                    ds.close();
                    if(updated){
                        Toast.makeText(getContext(),"Habit Configured Successfully",Toast.LENGTH_LONG);
                    }

                }catch(SQLException ex){
                    ex.printStackTrace();
                }
                getDialog().dismiss();
                Intent i = new Intent(getActivity(), HealthMainActivity.class);
                getActivity().startActivity(i);
            }
        });


        getDialog().setTitle("Configure Habit");
        EditText habitTitle = (EditText) view.findViewById(R.id.textHabit);
        habitTitle.setText(selectedHabit.getHabitname());


        ImageView habitIcon = (ImageView)view.findViewById(R.id.habitIcon);
        String uri = "@drawable/";
        if(!addCustomHabit) {
            uri+=selectedHabit.getHabiticon();  // where myresource (without the extension) is the file
        }else{
            uri+="plus";
        }
            int imageResource = view.getResources().getIdentifier(uri, null, getContext().getPackageName());
        habitIcon.setImageResource(imageResource);

        final TextView habitMin = (TextView) view.findViewById(R.id.textMin);
        if(!addCustomHabit) {
            habitMin.setText(String.valueOf(selectedHabit.getMin()));
        }else{
            habitMin.setText(String.valueOf(0));
        }
        final TextView habitMax = (TextView) view.findViewById(R.id.textMax);
        if(!addCustomHabit) {

            habitMax.setText(String.valueOf(selectedHabit.getMax()));
        }else{
            habitMax.setText(String.valueOf(0));
        }
        final EditText habitMinScale = (EditText) view.findViewById(R.id.textMinScale);
        habitMinScale.setText(selectedHabit.getScale());

        final EditText habitMaxScale = (EditText) view.findViewById(R.id.TextMaxScale);
        habitMaxScale.setText(selectedHabit.getScale());

        Button buttonMinusMin = (Button) view.findViewById(R.id.buttonMinusMin);
        buttonMinusMin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(habitMin.getText().toString());
                value--;
                if(value<0){
                    value=0;
                }
                int maxVal = Integer.parseInt(habitMax.getText().toString());
                habitMin.setText(String.valueOf(value));
            }
        });

        Button buttonPlusMin = (Button) view.findViewById(R.id.buttonPlusMin);
        buttonPlusMin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(habitMin.getText().toString());
                value++;
                int maxVal = Integer.parseInt(habitMax.getText().toString());
                if(value>maxVal){
                    value=maxVal;
                }
                habitMin.setText(String.valueOf(value));
            }
        });

        Button buttonMinusMax = (Button) view.findViewById(R.id.buttonMinusMax);

        buttonMinusMax.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(habitMax.getText().toString());
                value--;
                if(value<0){
                    value=0;
                }

                int minVal = Integer.parseInt(habitMin.getText().toString());
                if(value<minVal){
                    value=minVal;
                }

                habitMax.setText(String.valueOf(value));
            }
        });

        Button buttonPlusMax = (Button) view.findViewById(R.id.buttonPlusMax);
        buttonPlusMax.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(habitMax.getText().toString());
                value++;
                habitMax.setText(String.valueOf(value));
            }
        });

        Button saveButton = (Button) view.findViewById(R.id.buttonNext);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int maxVal = Integer.parseInt(habitMax.getText().toString());
                int minVal = Integer.parseInt(habitMin.getText().toString());
                DataSource ds = new DataSource(getContext());
                try {
                    ds.open();
                    boolean updated = false;
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String username = preferences.getString("username","");


                    if(addCustomHabit){
                        selectedHabit.setMin(minVal);
                        selectedHabit.setMax(maxVal);
                        ds.insertCustomHabit(selectedHabit,username);
                    }else {
                        updated = ds.updateHabit(minVal, maxVal, selectedHabitId, username);
                    }ds.close();
                    if(updated){
                        Toast.makeText(getContext(),"Habit Configured Successfully",Toast.LENGTH_LONG);
                    }

                }catch(SQLException ex){
                    ex.printStackTrace();
                }
                getDialog().dismiss();
                Intent i = new Intent(getActivity(), HealthMainActivity.class);
                getActivity().startActivity(i);

            }
        });
        Button cancelButton = (Button) view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new OnClickListener() {
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
