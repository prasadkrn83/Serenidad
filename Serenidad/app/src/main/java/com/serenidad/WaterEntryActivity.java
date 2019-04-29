package com.serenidad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class WaterEntryActivity extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_entry);
        id = getIntent().getStringExtra("id");
        initDiary();

        TextView note =(TextView) findViewById(R.id.textNote);
        TextView act =(TextView) findViewById(R.id.textAct);

        note.setText("Note: "+userDiary.get(0).getNote());
        act.setText("Act Of Kindness: "+userDiary.get(0).getAct());

    }

    ArrayList<Diary> userDiary;
    private void initDiary() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            userDiary = ds.getUserNoteById(id);
            ds.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
