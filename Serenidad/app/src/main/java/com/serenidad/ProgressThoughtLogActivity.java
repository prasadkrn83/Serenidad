package com.serenidad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgressThoughtLogActivity extends AppCompatActivity {

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_thought_log);

        id = getIntent().getStringExtra("id");
        initThought();

        TextView note =(TextView) findViewById(R.id.textNote);
        TextView act =(TextView) findViewById(R.id.textAct);

        note.setText("Note: "+userDiary.get(0).getNote());
        note.setText("Act Of Kindness: "+userDiary.get(0).getAct());
    }

    ArrayList<JournalThoughts> userEmotions;
    private void initThought() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            userEmotions = ds.getUserNoteById(id);
            ds.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
