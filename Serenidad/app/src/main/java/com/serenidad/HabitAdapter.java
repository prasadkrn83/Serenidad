package com.serenidad;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class HabitAdapter extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context adapterContext;
    private FragmentManager fm;

    public HabitAdapter(Context context, ArrayList<Habit> habits,FragmentManager fm) {
        super(context, R.layout.list_habit_item, habits);
        adapterContext = context;
        this.habits = habits;
        this.fm=fm;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
         View v = convertView;
        try {
            final Habit habit = habits.get(position);
            final int userid=1;
            final int habitId=habit.getHabitid();
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_habit_item, null);
            }
            final View thisView= v;
            TextView habitName = (TextView) v.findViewById(R.id.textHabit);
            habitName.setText(habit.getHabitname());
            ImageView icon = (ImageView)v.findViewById(R.id.habitIcon);
            String uri = "@drawable/"+habit.getHabiticon();  // where myresource (without the extension) is the file
            int imageResource = v.getResources().getIdentifier(uri, null, getContext().getPackageName());
            icon.setImageResource(imageResource);

            final TextView textMin = (TextView) v.findViewById(R.id.textMin);
            textMin.setText(String.valueOf(0));
            final TextView textMax = (TextView) v.findViewById(R.id.textMax);
            textMax.setText(String.valueOf(habit.getMax()));
            Button editButton = (Button)v.findViewById(R.id.buttonEditHabit);

            final TextView textHabitValue = (TextView) v.findViewById(R.id.textHabitValue);
            if(habit.getValue()>0){
                textHabitValue.setText(habit.getValue()+" "+habit.getScale());
            }

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                /*    Intent intent = new Intent(NewHabitActivity.this, ConfigureHabitDialog.class);
                    intent.putExtra("selectedHabit",selectedHabit );
                    startActivity(intent);*/

                    ConfigureHabitDialog configureHabitDialog = new ConfigureHabitDialog();
                    Bundle args = new Bundle();
                    args.putSerializable("habit",habit);
                    args.putBoolean("fromEditButton",true);
                    configureHabitDialog.setArguments(args);
                    configureHabitDialog.show(fm, "ConfigureHabitDialog");
                }
            });

            final int MAX=habit.getMax();
            final int MIN=0;
            final int TARGET_MIN=habit.getMin();
            final String scale = habit.getScale();
            final TextView value = (TextView)v.findViewById(R.id.textHabitValue);
            final int step=1;

            SeekBar seekbar = (SeekBar) v.findViewById(R.id.habitSeekBar);
            seekbar.setMax( (MAX- MIN) / step );
            seekbar.setProgress(habit.getValue()/step);

            if(habit.getValue()<TARGET_MIN) {
                seekbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }else{
                seekbar.getProgressDrawable().setColorFilter(thisView.getResources().getColor( R.color.darkGreen), PorterDuff.Mode.SRC_IN);
            }
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {

                    int actualProgress=MIN + (progress * step);
                    value.setText(String.valueOf(actualProgress)+" "+ scale);
                    if(actualProgress<TARGET_MIN) {
                        seekBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    }else{
                        seekBar.getProgressDrawable().setColorFilter(thisView.getResources().getColor( R.color.darkGreen), PorterDuff.Mode.SRC_IN);
                    }
                    DataSource ds = new DataSource(getContext());
                    try {
                        ds.open();
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String username = preferences.getString("username","");

                        ds.updateHabitProgress(username,habitId,actualProgress);
                        ds.close();
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }

                }
            });



        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }




}
