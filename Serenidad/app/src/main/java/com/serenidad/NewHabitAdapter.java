package com.serenidad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael Eierman on 11/14/2016.
 */

public class NewHabitAdapter extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context adapterContext;

    public NewHabitAdapter(Context context, ArrayList<Habit> habits) {
        super(context, R.layout.list_new_habit_item, habits);
        adapterContext = context;
        this.habits = habits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            Habit habit = habits.get(position);
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_new_habit_item, null);
            }

            TextView habitName = (TextView) v.findViewById(R.id.textHabit);
            habitName.setText(habit.getHabitname());
            ImageView icon = (ImageView)v.findViewById(R.id.habitIcon);
            String uri = "@drawable/"+habit.getHabiticon();  // where myresource (without the extension) is the file
            int imageResource = v.getResources().getIdentifier(uri, null, getContext().getPackageName());
            icon.setImageResource(imageResource);

        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }




}
