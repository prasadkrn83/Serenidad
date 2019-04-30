package com.serenidad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    EditText newPass;
    EditText confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        newPass=(EditText)findViewById(R.id.newPass);
        confirmPass=(EditText)findViewById(R.id.confPass);
    }


    public void saveNewPassword(View view){
        DataSource ds = new DataSource(getApplicationContext());
        String newPassword=newPass.getText().toString();
        String confirmPassword=confirmPass.getText().toString();

        if(newPassword.equals("")||confirmPassword.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enter password",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else if(!newPassword.equals(confirmPassword)){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Password mismatch",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            try {
                ds.open();
                ds.updateUserPass(newPassword,getIntent().getStringExtra("email"));
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Password saved successfully",
                        Toast.LENGTH_SHORT);
                toast.show();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ds.close();
            }
        }
    }
}
