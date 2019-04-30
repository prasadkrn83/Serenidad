package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}
