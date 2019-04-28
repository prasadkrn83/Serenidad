package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class journalofthot_emotionmeter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_emotionmeter);


    }

    public void setHappyFeeling(View v)

    {
        Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
        intent.putExtra("message", "Happy");
        startActivity(intent);
    }

    public void setVHappyFeeling(View v)

    {
        Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
        intent.putExtra("message", "VeryHappy");
        startActivity(intent);
    }

    public void setSadFeeling (View v)

    {
        Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
        intent.putExtra("message", "Sad");
        startActivity(intent);
    }
    public void setVSadFeeling(View v)

    {
        Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
        intent.putExtra("message", "VerySad");
        startActivity(intent);
    }
    public void setCrazyFeeling(View v)

    {
        Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
        intent.putExtra("message", "Crazy");
        startActivity(intent);
    }


}
