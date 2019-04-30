package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    ImageButton actionBarBack;
    TextView actionBarBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        actionBarBack = findViewById(R.id.action_bar_back);
        actionBarBack1 = findViewById(R.id.action_bar_back1);
        actionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public void selectButton(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.firstImageView:
                // do your code
                intent = new Intent(getApplicationContext(), journalofthot_emotionmeter.class);
                startActivity(intent);
                break;

            case R.id.secondImageView:
                // do your code
                intent = new Intent(getApplicationContext(), HealthMainActivity.class);
                startActivity(intent);
                break;

            case R.id.thirdImageView:
                // do your code
                intent = new Intent(getApplicationContext(), MeditationSelectionActivity.class);
                startActivity(intent);
                break;
            case R.id.fourthImageView:
                // do your code
                intent = new Intent(getApplicationContext(), ThankfulDiary.class);
                startActivity(intent);
                break;

            case R.id.fifthImageView:
                // do your code
              /*  intent = new Intent(getApplicationContext(), MeditationSelectionActivity.class);
                startActivity(intent);*/
                break;

            case R.id.sixthImageView:
                // do your code
                intent = new Intent(getApplicationContext(), ProgressChartActivity.class);
                startActivity(intent);
                break;
            case R.id.sevenImageView:
                // do your code
                /*intent = new Intent(getApplicationContext(), MeditationSelectionActivity.class);
                startActivity(intent);*/
                break;
            case R.id.eightImageView:

                intent = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
    @Override
    public void onBackPressed()
    {
        Log.d("OnBackPressed Called", "inside onbackpressed");
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
