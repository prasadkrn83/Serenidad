package com.serenidad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class journalofthot_feeling extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "prefeel";
    public static final String epref = "emotion";
    public static final String emoticon = "Ekey";
    public static final String feel = "FKey";

    public String fb2="";
    public String fb3="";
    public String fb4="";
    public String fb5="";
    public String fb6="";
    public String fb7="";
    public  String feelings=fb2+fb3+fb4+fb5+fb6+fb7;

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_feeling);



        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        // T.setText(message);

        sharedpreferences = getSharedPreferences(epref,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(emoticon, message);
        editor.commit();

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        setfeelingsbutton(message);







    }

    public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {

    }



    public void setfeelingsbutton(String s)
    { final ToggleButton b2 = (ToggleButton)findViewById(R.id.toggleButton2);
        final ToggleButton b3 = (ToggleButton)findViewById(R.id.toggleButton3);
        final ToggleButton b4 = (ToggleButton)findViewById(R.id.toggleButton4);
        final ToggleButton b5 = (ToggleButton)findViewById(R.id.toggleButton5);
        final ToggleButton b6 = (ToggleButton)findViewById(R.id.toggleButton6);
        final ToggleButton b7 = (ToggleButton)findViewById(R.id.toggleButton7);

        b2.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {

                    fb2=b2.getText().toString();
                   // setFeelings(getFeelings()+b2.getText().toString());

                }else
                {
                    fb2="";
                   // setFeelings(getFeelings()+"");
                }

            }
        }) ;
        b3.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {

                    fb3=b3.getText().toString();
                }else
                {
                    fb3="";// setFeelings(getFeelings()+"");
                }

            }
        }) ;

        b4.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {
                    fb4=b4.getText().toString();
                }else
                {
                    fb4="";
                }

            }
        }) ;

        b5.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {
                    fb5=b5.getText().toString();
                }else
                {
                    fb5="";
                }

            }
        }) ;

        b6.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {
                   fb6=b6.getText().toString();
                }else
                {
                    fb6="";
                }

            }
        }) ;

        b7.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {
                 fb7=b7.getText().toString();
                }else
                {
                  fb7="";
                }

            }
        }) ;


        String []arrayemotions={};
        if (s.equals("Happy")) {
            String[] arrhappy = {"Cheerful", "Awesome", "Nice", "Good", "Energetic"};
            arrayemotions=arrhappy;
        }
        else if(s.equals("Sad")) {
            String[] arrbad = {"Crazy", "Dull", "Sleepy", "Nervous", "Bored"};
            arrayemotions=arrbad;

        }
        else if(s.equals("VerySad")) {
            String[] arrbad = {"Unhappy", "Gloomy", "Sick", "Down", "Crushed"};
            arrayemotions=arrbad;

        }
        else if(s.equals("Crazy")) {
            String[] arrbad = {"Mourning", "Rueful", "Dead", "Angry", "Annoyed"};
            arrayemotions=arrbad;

        }
        else if(s.equals("VeryHappy")) {
            String[] arrbad = {"Great", "Sporty", "OnTop", "Wow", "Blissful"};
            arrayemotions=arrbad;

        }


        b2.setActivated(true);
        b2.setText(s);
        b2.setTextOff(s);
        b2.setTextOn(s);
        b2.setChecked(true);
        b2.setEnabled(false);

        b3.setActivated(true);
        b3.setText(arrayemotions[0].toString());
        b3.setTextOff(arrayemotions[0].toString());
        b3.setTextOn((arrayemotions[0].toString()));

        b4.setActivated(true);
        b4.setText(arrayemotions[1].toString());
        b4.setTextOff(arrayemotions[1].toString());
        b4.setTextOn((arrayemotions[1].toString()));

        b5.setActivated(true);
        b5.setText(arrayemotions[2].toString());
        b5.setTextOff(arrayemotions[2].toString());
        b5.setTextOn((arrayemotions[2].toString()));

        b6.setActivated(true);
        b6.setText(arrayemotions[3].toString());
        b6.setTextOff(arrayemotions[3].toString());
        b6.setTextOn((arrayemotions[3].toString()));

        b7.setActivated(true);
        b7.setText(arrayemotions[4].toString());
        b7.setTextOff(arrayemotions[4].toString());
        b7.setTextOn((arrayemotions[4].toString()));

    }


    public void OnSave(View V)
    {   String s="";

        SharedPreferences.Editor editor = sharedpreferences.edit();
        TextView T = (TextView)findViewById(R.id.feelingtxt);
        s=fb2+""+fb3+""+fb4+""+fb5+""+fb6+""+fb7+""; //getFeelings().toString();
        editor.putString(feel, s);
        editor.commit();
        T.setText(s);
        Intent intent = new Intent(getApplicationContext(),journalofthot_datepick.class);
        startActivity(intent);




    }



}
