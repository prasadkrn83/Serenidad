package com.serenidad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class HealthMainActivity extends AppCompatActivity {

    ImageButton actionBarBack;
    ImageButton helpIcon;

    TextView actionBarBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_activity_main);

        initAddHabitButton();
        initHabits();
        initBackActionBar();

    }

    private void initBackActionBar() {

        actionBarBack = findViewById(R.id.action_bar_back);
        helpIcon = findViewById(R.id.help_icon);
        actionBarBack1 = findViewById(R.id.action_bar_back1);
        actionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick pressed", "onbackpressed");
                onBackPressed();
            }
        });

        actionBarBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        helpIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthMainActivity.this, HealthHelpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    private void initHabits() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            ArrayList<Habit> userHabits = ds.getUserHabits();
            ds.close();
            if(userHabits!=null && userHabits.size()>0){
                FragmentManager fm = getSupportFragmentManager();
                HabitAdapter adapter = new HabitAdapter(this, userHabits,fm);
                ListView habitList = (ListView)findViewById(R.id.userHabitList);
                habitList.setAdapter(adapter);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private void initAddHabitButton(){

    Button addNewHabitBtn = (Button) findViewById(R.id.buttonNewHabbit);
    addNewHabitBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HealthMainActivity.this, NewHabitActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    });
    }

    @Override
    public void onBackPressed()
    {
        Log.d("OnBackPressed Called", "inside onbackpressed");
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
