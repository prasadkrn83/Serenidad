package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MeditationSelectionActivity extends AppCompatActivity {
    ImageButton actionBarBack;
    TextView actionBarBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_selection);
        actionBarBack = findViewById(R.id.action_bar_back);
        actionBarBack1 = findViewById(R.id.action_bar_back1);
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
    public void selectButton(View view){
        Intent intent = new Intent(getApplicationContext(), MeditationActivity.class);
        switch (view.getId()) {

            case R.id.firstImageView:
                // do your code
                intent.putExtra("meditation",1);
                startActivity(intent);
                break;

            case R.id.secondImageView:
                // do your code
                intent.putExtra("meditation",2);
                startActivity(intent);
                break;

            case R.id.thirdImageView:
                // do your code
                intent.putExtra("meditation",3);
                startActivity(intent);
                break;
            case R.id.fourthImageView:
                // do your code
                intent.putExtra("meditation",4);
                startActivity(intent);
                break;

            case R.id.fifthImageView:
                // do your code
                intent.putExtra("meditation",5);
                startActivity(intent);
                break;

            case R.id.sixthImageView:
                // do your code
                intent.putExtra("meditation",6);
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
