package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class ProgressChartActivity extends AppCompatActivity {

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;

    ImageButton actionBarBack;
    ImageButton actionBarForward;
    TextView actionBarBack1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_chart);

        chart = (BarChart) findViewById(R.id.chart1);

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

    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(3f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(8f, 2));
        BARENTRY.add(new BarEntry(4f, 3));
        BARENTRY.add(new BarEntry(7f, 4));
        BARENTRY.add(new BarEntry(3f, 5));

    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("8/11");
        BarEntryLabels.add("9/11");
        BarEntryLabels.add("10/11");
        BarEntryLabels.add("11/11");
        BarEntryLabels.add("12/11");
        BarEntryLabels.add("13/11");

    }

    @Override
    public void onBackPressed()
    {
        Log.d("OnBackPressed Called", "inside onbackpressed");
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
