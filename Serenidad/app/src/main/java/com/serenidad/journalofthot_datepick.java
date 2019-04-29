package com.serenidad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
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
        v.setDate(Calendar.getInstance().getTimeInMillis(),false,true); //SUNDAY); //SUNDAY
        v.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String m = "";
                String d = "";
                int temp = month+1;
                // TODO Auto-generated method stub
                //date =( dayOfMonth +"/" + (month+1) + "/" + year);
                if(month < 10){

                    m = "0" + temp;
                }
                else
                    m=""+temp;
                if(dayOfMonth < 10){

                    d  = "0" + dayOfMonth ;
                }
                else
                    d=""+dayOfMonth;
                date =( year +"-" + m + "-" + d);
                //searchText.setText(day + "-" + month + "-" + year);
                t.setText(date);
            }
        });

    }


    public void OnSave(View v) {
        SharedPreferences.Editor editor = sharedpreferences.edit();

        if(date.equalsIgnoreCase(""))
            date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        editor.putString(userdate, date);
        editor.commit();
        Intent intent = new Intent(getApplicationContext(),journalofthot_situation.class);
        startActivity(intent);


    }








}
