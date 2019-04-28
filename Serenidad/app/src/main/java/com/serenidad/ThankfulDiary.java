package com.serenidad;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class ThankfulDiary extends AppCompatActivity {

    private String user_name = "1";
    ImageButton actionBarBack;
    ImageButton actionBarForward;
    TextView actionBarBack1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankful_diary);
        initButton();

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

    private void initButton(){

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource ds = new DataSource(getApplicationContext());
                try {
                    ds.open();
                    EditText txtNote = (EditText) findViewById(R.id.textNote);
                    EditText txtAct = (EditText) findViewById(R.id.textAct);
                    if (txtNote.getText().toString().isEmpty() || txtAct.getText().toString().isEmpty())
                        return;
                    else {
                        Boolean result;
                        result = ds.InsertNote(user_name, txtNote.getText().toString(), txtAct.getText().toString());
                        if(result){
                            txtAct.setText("");
                            txtNote.setText("");
                        }
                    }
                    ds.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtNote = (EditText) findViewById(R.id.textNote);
                EditText txtAct = (EditText) findViewById(R.id.textAct);

                txtAct.setText("");
                txtNote.setText("");
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Log.d("OnBackPressed Called", "inside onbackpressed");
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
