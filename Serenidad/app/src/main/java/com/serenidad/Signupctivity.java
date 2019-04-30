package com.serenidad;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Signupctivity extends AppCompatActivity {
    private EditText editFName, editLName, editEmail, editPass, editCPass, editPhone;
    private Button btnNext;
    private TextView txtAge;
    private ImageView imgProfile, imgAgePlus, imgAgeMinus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupctivity);
        editFName = findViewById(R.id.editFName);
        editLName = findViewById(R.id.editLName);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        editCPass = findViewById(R.id.editCPass);
        editPhone = findViewById(R.id.editPhone);
        btnNext = findViewById(R.id.btnNext);
        txtAge = findViewById(R.id.txtAge);
        imgProfile = findViewById(R.id.imgProfile);
        imgAgeMinus = findViewById(R.id.imgAgeMinus);
        imgAgePlus = findViewById(R.id.imgAgePlus);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editFName.getText().toString().isEmpty()
                        & !editLName.getText().toString().isEmpty()
                        & !editEmail.getText().toString().isEmpty()
                        & !editPass.getText().toString().isEmpty()
                        & !editCPass.getText().toString().isEmpty()
                        & !editPhone.getText().toString().isEmpty()){
                    if(editCPass.getText().toString().equals(editPass.getText().toString())){
                        SharedPreferenceHelper.setLogedIn(Signupctivity.this,true);
                        SharedPreferenceHelper.setAge(Signupctivity.this,txtAge.getText().toString());
                        SharedPreferenceHelper.setEmail(Signupctivity.this,editEmail.getText().toString());
                        SharedPreferenceHelper.setFName(Signupctivity.this,editFName.getText().toString());
                        SharedPreferenceHelper.setLname(Signupctivity.this, editLName.getText().toString());
                        SharedPreferenceHelper.setNumber(Signupctivity.this, editPhone.getText().toString());
                        SharedPreferenceHelper.setPass(Signupctivity.this,editPass.getText().toString());
                        Intent intent = new Intent(Signupctivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signupctivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        imgAgePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(txtAge.getText().toString());
                age++;
                txtAge.setText(""+age);
            }
        });

        imgAgeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(txtAge.getText().toString());
                age--;
                if(age>0){
                    txtAge.setText(""+age);
                }

            }
        });
    }}
