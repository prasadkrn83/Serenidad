package com.serenidad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class journalofthot_datepick extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "datepref";
    public static final String userdate = "dateKey";
    public   String date="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_datepick);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        final TextView t = (TextView)findViewById(R.id.textView14);
        CalendarView v = (CalendarView) findViewById(R.id.CalenderObj);
        v.setFirstDayOfWeek(1); //SUNDAY
        v.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub
                date =( dayOfMonth +" / " + (month+1) + " / " + year);
                t.setText(date);
            }
        });

    }


    public void OnSave(View v) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(userdate, date);
        editor.commit();
        Intent intent = new Intent(getApplicationContext(),journalofthot_situation.class);
        startActivity(intent);


    }








}
