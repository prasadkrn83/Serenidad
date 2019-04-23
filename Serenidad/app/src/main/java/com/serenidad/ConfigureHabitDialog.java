package com.serenidad;

/**
 * Created by Michael Eierman on 11/2/2016.
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

    public interface ConfigureHabitListener {                                         //2
        void didFinishConfiureHabitDialog(int min, int max, String scale);
    }

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

        Habit selectedHabit=null;
        boolean isFromEditButton = false;
        boolean addCustomHabit = false;
        final View view = inflater.inflate(R.layout.configure_habit,container);

        Button deleteButton = (Button)view.findViewById(R.id.buttonDelete);

        if (getArguments() != null) {
            selectedHabit = (Habit)getArguments().getSerializable("habit");
            isFromEditButton = getArguments().getBoolean("fromEditButton");
            addCustomHabit = getArguments().getBoolean("addCustomHabit");
            if(isFromEditButton){
                deleteButton.setEnabled(true);
            }
        }else{
            getDialog().dismiss();
        }

        final int selectedHabitId=selectedHabit.getHabitid();

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
        if(!addCustomHabit) {
            habitTitle.setText(selectedHabit.getHabitname());
        }else{
            habitTitle.setText("Habit Name");
            habitTitle.setEnabled(true);
            habitTitle.setTextSize(18);

        }


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
        if(!addCustomHabit) {
            habitMinScale.setText(selectedHabit.getScale());

        }else{
            habitMinScale.setText("Custom Scale");
            habitMinScale.setEnabled(true);
            habitMinScale.setTextSize(18);


        }
        final EditText habitMaxScale = (EditText) view.findViewById(R.id.TextMaxScale);
        if(!addCustomHabit) {
            habitMaxScale.setText(selectedHabit.getScale());

        }else{
            habitMaxScale.setText("Custom Scale");
            habitMaxScale.setEnabled(true);
            habitMaxScale.setTextSize(18);

        }

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

        Button saveButton = (Button) view.findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int maxVal = Integer.parseInt(habitMax.getText().toString());
                int minVal = Integer.parseInt(habitMin.getText().toString());
                DataSource ds = new DataSource(getContext());
                try {
                    ds.open();
                    boolean updated = ds.updateHabit(minVal,maxVal,selectedHabitId,1);
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

    private void didFinishConfiureHabitDialog(int min, int max, String scale) {                               //6
        ConfigureHabitListener activity = (ConfigureHabitListener) getActivity();
        activity.didFinishConfiureHabitDialog(min, max, scale);
        getDialog().dismiss();
    }
}
