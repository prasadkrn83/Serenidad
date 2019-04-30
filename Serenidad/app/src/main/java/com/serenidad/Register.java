package com.serenidad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText emailId;
    EditText username;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText)findViewById(R.id.username);
        emailId=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        confirmPassword=(EditText)findViewById(R.id.confirmPassword);
    }

    public void registerUser(View view){
        DataSource ds = new DataSource(getApplicationContext());
        String uname=username.getText().toString();
        String pass=password.getText().toString();
        String email=emailId.getText().toString();
        String confirmPass=confirmPassword.getText().toString();

        if(email.equals("")|| uname.equals("")||pass.equals("") ||confirmPass.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enter details",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            if(pass.equals(confirmPass)) {
                try{
                    ds.open();
                    if (ds.isUserRegistered(email)) {
                        ds.insertUser(uname, pass, email);
                        Intent intent = new Intent(this, login.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "User already present",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    ds.close();
                }
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "password mismatch",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
