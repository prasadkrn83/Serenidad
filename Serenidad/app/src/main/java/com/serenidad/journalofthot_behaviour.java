package com.serenidad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLException;

public class journalofthot_behaviour extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferencesdef;


    TextView name;
    TextView email;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    /**Shared Pref Key Val**/
    public static final String datepref = "datepref";
    public static final String userdate = "dateKey";
    public static final String feelpref = "prefeel";
    public static final String feel = "FKey";
    public static final String situationpref = "sitpref";
    public static final String Whom = "perker";
    public static final String Where = "placeKey";
    public static final String When = "dateKey";
    public static final String epref = "emotion";
    public static final String emoticon = "Ekey";
    /**Shared Pref Key Val**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_behaviour);
        name = (TextView) findViewById(R.id.editText_Person);
        email = (TextView) findViewById(R.id.editText_Place);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


    }

    public void Save(View view) {
        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.commit();
        saveindatabase();
       /* Intent intent = new Intent(getApplicationContext(), journalofthot_help.class);
        startActivity(intent);*/


    }

    public void clear(View view) {
        name = (TextView) findViewById(R.id.editText_Person);
        email = (TextView) findViewById(R.id.editText_Place);
        name.setText("");
        email.setText("");

    }

    public void Get(View view) {
        name = (TextView) findViewById(R.id.editText_Person);
        email = (TextView) findViewById(R.id.editText_Place);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
    }


    public void saveindatabase() {
        TextView t = (TextView) findViewById(R.id.txtbox_pref);
        String emotion;
        String feelings;
        String thoughtDate;
        String situatationWhom;
        String situationWhen;
        String situationWhere;
        String behaviourAfterThought;
        String behaviourReaction;

        sharedpreferencesdef = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        sharedpreferences = getSharedPreferences(feelpref,
                Context.MODE_PRIVATE);
        feelings = sharedpreferences.getString(feel, "");

        sharedpreferences = getSharedPreferences(datepref,
                Context.MODE_PRIVATE);
        thoughtDate=sharedpreferences.getString(userdate, "");
        sharedpreferences = getSharedPreferences(situationpref,
                Context.MODE_PRIVATE);
                situatationWhom =sharedpreferences.getString(Whom,"");
                situationWhen=sharedpreferences.getString(Where,"");
                situationWhere=sharedpreferences.getString(When,"");
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        sharedpreferences = getSharedPreferences(epref,
                Context.MODE_PRIVATE);
               emotion=sharedpreferences.getString(emoticon,"");
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        behaviourAfterThought=sharedpreferences.getString(Name, "");
        behaviourReaction=sharedpreferences.getString(Email, "");

        t.setText(emotion+feelings+ thoughtDate+ situatationWhom +situationWhen +situationWhere +behaviourAfterThought +behaviourReaction);
        DataSource d = new DataSource(getApplicationContext());
        JournalThoughts j = new JournalThoughts();
        String uname= sharedpreferencesdef.getString("username","");
        try {
            d.open();
            //j.setThoughtId();




            j.setUserName(uname);
            j.setEmotion(emotion);
            j.setFeelings(feelings);
            j.setThoughtDate(thoughtDate);
            j.setSituatationWhom(situatationWhom);
            j.setSituationWhen(situationWhere);
            j.setSituationWhere(situationWhen);
            j.setBehaviourAfterThought(behaviourAfterThought);
            j.setGetBehaviourReaction(behaviourReaction);


            // d.insertEmotiont(emotion+feelings+ thoughtDate+ situatationWhom +situationWhen +situationWhere +behaviourAfterThought +getBehaviourReaction);
            d.insertEmotiont(j);
            d.close();
//        d.insertEmotiont(emotion+":"+feelings+":"+ thoughtDate+":"+ situatationWhom +":"+situationWhen +":"+situationWhere +":"+behaviourAfterThought +":"+getBehaviourReaction);
            Intent intent = new Intent(getApplicationContext(), journalofthot_help.class);
            startActivity(intent);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}







