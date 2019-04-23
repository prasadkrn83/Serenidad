package com.serenidad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewHabitActivity extends FragmentActivity {
    ArrayList<Habit> habits;
    boolean isDeleting = false;
    NewHabitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhabit);
        initButtonClose();
        initButtonAddNewHabit();
    }

    private void initButtonAddNewHabit() {
        Button addNewHabitBtn = (Button)findViewById(R.id.buttonAddCustomHabit);
        addNewHabitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                ConfigureHabitDialog configureHabitDialog = new ConfigureHabitDialog();
                Bundle args = new Bundle();
                args.putBoolean("addCustomHabit",true);
                configureHabitDialog.setArguments(args);
                configureHabitDialog.show(fm, "ConfigureHabitDialog");
            }
        });
    }

    private void initButtonClose() {
        Button buttonClose = (Button) findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewHabitActivity.this, HealthMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        DataSource ds = new DataSource(this);
        try {
            ds.open();
            habits = ds.getDefaultHabits();
            ds.close();
            adapter = new NewHabitAdapter(this, habits);
            ListView habitList = (ListView)findViewById(R.id.newhabit);
            habitList.setAdapter(adapter);


        if (habits.size() > 0) {
            //ListView listView = getListView();
            habitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                    Habit selectedHabit= habits.get(position);
                /*    Intent intent = new Intent(NewHabitActivity.this, ConfigureHabitDialog.class);
                    intent.putExtra("selectedHabit",selectedHabit );
                    startActivity(intent);*/

                    FragmentManager fm = getSupportFragmentManager();
                    ConfigureHabitDialog configureHabitDialog = new ConfigureHabitDialog();
                    Bundle args = new Bundle();
                    args.putSerializable("habit",selectedHabit);
                    configureHabitDialog.setArguments(args);
                    configureHabitDialog.show(fm, "ConfigureHabitDialog");
                }

            });
        }
        else {
            Intent intent = new Intent(NewHabitActivity.this, HealthMainActivity.class);
            startActivity(intent);
        }

        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving default habits", Toast.LENGTH_LONG).show();
        }

    }

}
