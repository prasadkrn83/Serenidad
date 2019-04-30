package com.serenidad;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmailPassword extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);
        email=(EditText)findViewById(R.id.resetEmail);
    }

    public void sendEmail(View view){
        DataSource ds = new DataSource(getApplicationContext());
        String emailId=email.getText().toString();

        if(emailId.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enter email",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            try {
                ds.open();
                if(ds.isUserRegistered(emailId)){
                    Intent intent = new Intent(this, ForgotPassword.class);
                    intent.putExtra("email",emailId);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "please check email",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "please enter correct email",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ds.close();
            }
        }
    }

}
