package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class journalofthot_help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalofthot_help);
    }


    public void BackHome(View V)
    {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);


    }


}

