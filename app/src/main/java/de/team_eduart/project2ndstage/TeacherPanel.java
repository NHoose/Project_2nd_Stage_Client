package de.team_eduart.project2ndstage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeacherPanel extends ActionBarActivity {

    Button SendFileButton;
    Button LockAppsButton;
    Button UnlockAppsButton;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBlank);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SendFileButton = (Button) findViewById(R.id.BtnSendFile);
        LockAppsButton = (Button) findViewById(R.id.BtnLockApps);
        UnlockAppsButton = (Button) findViewById(R.id.BtnUnlockApps);

        final SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);

        final String ServerIP = NetworkState.getString("ServerIP", null);
        final String Username = NetworkState.getString("Username", "Lehrer");

        SendFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userid", Username));
                params.add(new BasicNameValuePair("operation", "senden"));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://"+ ServerIP +":8080/filesend",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "keine Serververbindung", Toast.LENGTH_LONG).show();
                }
            }
        });

        LockAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userid", Username));
                params.add(new BasicNameValuePair("operation", "sperren"));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://"+ ServerIP +":8080/appsperr",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "keine Serververbindung", Toast.LENGTH_LONG).show();
                }
            }
        });

        UnlockAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userid", Username));
                params.add(new BasicNameValuePair("operation", "entsperren"));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://"+ ServerIP +":8080/appsperr",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        if(json.getBoolean("res")){
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
