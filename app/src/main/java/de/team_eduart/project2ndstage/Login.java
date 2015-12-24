package de.team_eduart.project2ndstage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends ActionBarActivity {

    Button BtnLogin;
    EditText editTextUsername;
    EditText editTextPassword;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBlank);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);
        final SharedPreferences.Editor editor = NetworkState.edit();

        BtnLogin = (Button) findViewById(R.id.BtnLogin);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = editTextUsername.getText().toString();
                String Password = editTextPassword.getText().toString();

                String ServerIP = NetworkState.getString("ServerIP", null);

                /*Hardcoded als Test
                if (Username.equals("Admin")) {
                    if (Password.equals("1234")) {
                        editor.putBoolean("LoggedIn", true);
                        editor.putString("Username", Username);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Eingeloggt als " + Username, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "falsches Passwort", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "unbekannter Benutzername", Toast.LENGTH_SHORT).show();
                }
                */

                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userid", Username));
                params.add(new BasicNameValuePair("password", Password));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://"+ ServerIP +":8080/testlogin",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        if(json.getBoolean("res")){
                            editor.putBoolean("LoggedIn", true);
                            editor.putString("Username", Username);
                            editor.apply();
                            startService(new Intent(Login.this, AppLockService.class));
                            finish();
                        }

                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "keine Serververbindung", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
