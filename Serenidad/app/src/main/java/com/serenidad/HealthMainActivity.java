package com.serenidad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class HealthMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_activity_main);

        initAddHabitButton();
        initHabits();
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
}
