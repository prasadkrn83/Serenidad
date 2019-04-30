package com.serenidad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.PorterDuff;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.SQLException;


public class journalofthot_emotionmeter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_emotionmeter);


        final int MAX=10;
        final int MIN=0;
        final int step=2;

        SeekBar seekbar = (SeekBar) findViewById(R.id.emotionBar);
        seekbar.setMax( (MAX- MIN) / step );

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                ImageView v =  (ImageView)findViewById(R.id.emotionimg);

                int actualProgress=MIN + (progress * step);
                if(actualProgress<=2) {
                    v.setImageResource(R.drawable.crazy);
                    seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#E12D1F"), PorterDuff.Mode.SRC_IN);
                    Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
                    intent.putExtra("message", "Crazy");
                    startActivity(intent);
                }else if(actualProgress>2 && actualProgress<=4) {
                    v.setImageResource(R.drawable.vsad);
                    seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#C55A11"), PorterDuff.Mode.SRC_IN);
                    Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
                    intent.putExtra("message", "VerySad");
                    startActivity(intent);

                }else if(actualProgress>4 && actualProgress<=6) {
                    v.setImageResource(R.drawable.sad);
                    seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#E1C000"), PorterDuff.Mode.SRC_IN);
                    Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
                    intent.putExtra("message", "Sad");
                    startActivity(intent);
                }else if(actualProgress>6 && actualProgress<=8) {
                    v.setImageResource(R.drawable.happy);
                    seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#92D050"), PorterDuff.Mode.SRC_IN);
                    Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
                    intent.putExtra("message", "Happy");
                    startActivity(intent);
                }
                else if(actualProgress>8 && actualProgress<=10) {
                    v.setImageResource(R.drawable.vhappy);
                    seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#385723"), PorterDuff.Mode.SRC_IN);
                    Intent intent = new Intent(journalofthot_emotionmeter.this, journalofthot_feeling.class);
                    intent.putExtra("message", "VeryHappy");
                    startActivity(intent);
                }


            }
        });



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
