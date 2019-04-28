package com.serenidad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class journalofthot_situation extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView Person;
    TextView Place;
    TextView Date_time;
    public static final String mypreference = "sitpref";
    public static final String Whom = "perker";
    public static final String Where = "placeKey";
    public static final String When = "dateKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_situation);
        Person = (TextView) findViewById(R.id.editText_Person);
        Place = (TextView) findViewById(R.id.editText_Place);
        Date_time=(TextView) findViewById(R.id.editText_Datetime);
     sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
    }
    public void Save(View view) {
        String Pe = Person.getText().toString();
        String Pl = Place.getText().toString();
        String Dt = Place.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Whom, Pe);
        editor.putString(Where, Pl);
        editor.putString(When, Dt);
        editor.commit();
        Intent intent = new Intent(getApplicationContext(),journalofthot_behaviour.class);
        startActivity(intent);
    }

    public void clear(View view) {
        Person = (TextView) findViewById(R.id.editText_Place);
        Place = (TextView) findViewById(R.id.editText_Person);
        Date_time=  (TextView) findViewById(R.id.editText_Datetime);
        Person.setText("");
        Place.setText("");
        Date_time.setText("");

    }

}
