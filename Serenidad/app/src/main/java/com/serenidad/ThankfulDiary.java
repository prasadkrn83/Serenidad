package com.serenidad;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class ThankfulDiary extends AppCompatActivity {

    private String user_name = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankful_diary);
        initButton();
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
                    else
                        ds.InsertNote(user_name,txtNote.getText().toString(),txtAct.getText().toString());
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

}
