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

        TextView date =(TextView) findViewById(R.id.txtDate);
        TextView when =(TextView) findViewById(R.id.txtWhen);
        TextView where =(TextView) findViewById(R.id.txtLocation);
        TextView with =(TextView) findViewById(R.id.txtWith);
        TextView feeling =(TextView) findViewById(R.id.txtFeelings);
        TextView afterThought =(TextView) findViewById(R.id.txtAfterThought);
        TextView afterReaction =(TextView) findViewById(R.id.txtAfterReaction);
        TextView emotion =(TextView) findViewById(R.id.txtEmotion);

        date.setText(userEmotions.get(0).getThoughtDate());
        when.setText(userEmotions.get(0).getSituationWhere());
        where.setText(userEmotions.get(0).getSituationWhere());
        with.setText(userEmotions.get(0).getSituatationWhom());
        feeling.setText(userEmotions.get(0).getFeelings());
        afterThought.setText(userEmotions.get(0).getBehaviourAfterThought());
        afterReaction.setText(userEmotions.get(0).getGetBehaviourReaction());
        emotion.setText(userEmotions.get(0).getEmotion());
    }

    ArrayList<JournalThoughts> userEmotions;
    private void initThought() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            userEmotions = ds.getUserThoughtById(id);
            ds.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
