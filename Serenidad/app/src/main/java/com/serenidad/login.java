package com.serenidad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button btnLogin;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        username=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
    }


    public void authenticateUser(View view){
        DataSource ds = new DataSource(getApplicationContext());
        String uname=username.getText().toString();
        String pass=password.getText().toString();

        if(uname.equals("")||pass.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enter credentials",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            try {

                ds.open();
                boolean isValidUser = ds.authenticateUser(uname, pass);
                if (isValidUser) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", uname);
                    //editor.putString("Pass", pass);
                    editor.apply();
                    Intent intent = new Intent(this, Home.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Invalid Credentials",
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

    public void register(View view){
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }

/*    public void loginFB(View view){
        Collection<String> permissions = Arrays.asList("public_profile", "email");
        ParseFacebookUtils.logInWithReadPermissionsInBackground(login.this, permissions, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (err != null) {
                    ParseUser.logOut();
                    Log.e("err", "err", err);
                }
                if (user == null) {
                    ParseUser.logOut();
                    Toast.makeText(login.this, "The user cancelled the Facebook login.", Toast.LENGTH_LONG).show();
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Toast.makeText(login.this, "User signed up and logged in through Facebook.", Toast.LENGTH_LONG).show();

                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    getUserDetailFromFB();
                } else {
                    Toast.makeText(login.this, "User logged in through Facebook.", Toast.LENGTH_LONG).show();

                    Log.d("MyApp", "User logged in through Facebook!");
                    getUserDetailFromParse();
                }
            }
        });

    }


    void getUserDetailFromFB(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),new  GraphRequest.GraphJSONObjectCallback(){
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                ParseUser user = ParseUser.getCurrentUser();
                try{
                    user.setUsername(object.getString("name"));
                }catch(JSONException e){
                    e.printStackTrace();
                }
                try{
                    user.setEmail(object.getString("email"));
                }catch(JSONException e){
                    e.printStackTrace();
                }
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        alertDisplayer("First Time Login", "Welcome!");
                    }
                });
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }*/

/*    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(login.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(login.this, login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }*/
}
