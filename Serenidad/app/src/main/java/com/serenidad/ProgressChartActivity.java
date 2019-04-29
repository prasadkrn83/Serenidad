package com.serenidad;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.HashMap;
import java.util.Map;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProgressChartActivity extends AppCompatActivity {

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    private RecyclerView recyclerView;
    BarData BARDATA ;

    ImageButton actionBarBack;
    ImageButton actionBarForward;
    TextView actionBarBack1;

    private String user_name="1";
    private String AxisValue = "";
    private String xAxisValue = "";
    private String finalXAxisValue = "";

    private String[] listt = {"Water", "Thought Log", "Water", "Water", "Thought Log", "Thought Log", "Water", "Thought Log", "Water", "Thought Log"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_chart);
        userActivities = new ArrayList<Acitvity>();
        initHabits();
        initEmotions();
        initDiary();

        chart = (BarChart) findViewById(R.id.chart1);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        Bardataset = new BarDataSet(BARENTRY, "My Daily Progress");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(
                new int[]{
                        getResources().getColor(R.color.colorBar1),
                        getResources().getColor(R.color.colorBar2)
                });


        chart.setData(BARDATA);

        chart.animateY(3000);

        actionBarBack = findViewById(R.id.action_bar_back);
        actionBarBack1 = findViewById(R.id.action_bar_back1);

        actionBarForward = findViewById(R.id.action_bar_forward);
        actionBarForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d("Fragment Called", "onclick of meditation");
                Intent intent = new Intent(getApplicationContext(),ThankfulDiaryHelpActivity.class);
                startActivity(intent);
            }
        });

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


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                AxisValue = chart.getData().getXVals().get(e.getXIndex());
                xAxisValue = String.valueOf(e.getVal());
                finalXAxisValue = xAxisValue;
            }

            @Override
            public void onNothingSelected() {

            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String XAXIS = finalXAxisValue.substring(0, 1);
                String[] newString = Arrays.copyOfRange(listt, 0, Integer.parseInt(XAXIS));
                ArrayList<Acitvity> newList = filterbyDate(AxisValue);
                ArrayList<String> strings = new ArrayList<>(Arrays.asList(newString));
                ChartAdaptor homeAdapter = new ChartAdaptor(newList,getSupportFragmentManager() );
                ;
                recyclerView.setLayoutManager(new LinearLayoutManager(ProgressChartActivity.this));
                recyclerView.setAdapter(homeAdapter);
            }
        });
    }

    ArrayList<Habit> userHabits;
    ArrayList<Acitvity> userActivities;
    Map<String, Integer> habitdatesHM = new HashMap<String, Integer>();

    private void initHabits() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            userHabits = ds.getUserHabitsEntry(user_name);
            Acitvity temp;
            for (Habit i : userHabits) {
                Integer j = habitdatesHM.get(i.getDate());
                habitdatesHM.put(i.getDate(), (j == null) ? 1 : j + 1);

                temp = new Acitvity();
                temp.setActDate(i.getDate());
                temp.setActImage(i.getHabiticon());
                temp.setActResult(i.getScale());
                temp.setActTitle(i.getHabitname());
                temp.setActType("H");
                temp.setId(String.valueOf(i.getHabitid()));
                userActivities.add(temp);
            }
            ds.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    ArrayList<JournalThoughts> userEmotions;
    private void initEmotions() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            userEmotions = ds.getUserThought(user_name);
            Acitvity temp;
            for (JournalThoughts i : userEmotions) {
                Integer j = habitdatesHM.get(i.getThoughtDate());
                habitdatesHM.put(i.getThoughtDate(), (j == null) ? 1 : j + 1);

                temp = new Acitvity();
                temp.setActDate(i.getThoughtDate());
                temp.setActImage("emoji.png");
                temp.setActResult(i.getFeelings());
                temp.setActTitle("Thouoght");
                temp.setActType("E");
                temp.setId(String.valueOf(i.getThoughtId()));
                userActivities.add(temp);
            }
            ds.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    ArrayList<Diary> userDiary;
    private void initDiary() {
        DataSource ds = new DataSource(getApplicationContext());
        try {
            ds.open();
            userDiary = ds.getUserNote(user_name);

            Acitvity temp;
            for (Diary i : userDiary) {
                Integer j = habitdatesHM.get(i.getNoteDate());
                habitdatesHM.put(i.getNoteDate(), (j == null) ? 1 : j + 1);

                temp = new Acitvity();
                temp.setActDate(i.getNoteDate());
                temp.setActImage("ediit.png");
                temp.setActResult(i.getAct());
                temp.setActTitle("Diary");
                temp.setActType("D");
                temp.setId(String.valueOf(i.getNoteId()));
                userActivities.add(temp);
            }
            ds.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void AddValuesToBARENTRY(){

        for (Map.Entry<String, Integer> val : habitdatesHM.entrySet()) {

            BarEntryLabels.add(val.getKey());
            BARENTRY.add(new BarEntry(Float.valueOf(val.getValue().toString().trim()).floatValue(), 0));
        }
//        BARENTRY.add(new BarEntry(3f, 0));
//        BARENTRY.add(new BarEntry(4f, 1));
//        BARENTRY.add(new BarEntry(8f, 2));
//        BARENTRY.add(new BarEntry(4f, 3));
//        BARENTRY.add(new BarEntry(7f, 4));
//        BARENTRY.add(new BarEntry(3f, 5));

    }

    public void AddValuesToBarEntryLabels(){

//        BarEntryLabels.add("8/11");
//        BarEntryLabels.add("9/11");
//        BarEntryLabels.add("10/11");
//        BarEntryLabels.add("11/11");
//        BarEntryLabels.add("12/11");
//        BarEntryLabels.add("13/11");

    }

    public ArrayList<Acitvity> filterbyDate(String Date){

        ArrayList<Acitvity> BindingList = new ArrayList<Acitvity>();

        for(Acitvity i : userActivities){
            if(i.getActDate().equalsIgnoreCase(Date))
                BindingList.add(i);
        }
        return BindingList;
    }
    @Override
    public void onBackPressed()
    {
        Log.d("OnBackPressed Called", "inside onbackpressed");
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}


