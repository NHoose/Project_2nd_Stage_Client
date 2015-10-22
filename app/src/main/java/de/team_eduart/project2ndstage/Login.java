package de.team_eduart.project2ndstage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends ActionBarActivity {

    Button BtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);
        final SharedPreferences.Editor editor = NetworkState.edit();

        BtnLogin = (Button) findViewById(R.id.BtnLogin);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("LoggedIn", true);
                editor.apply();

                Toast.makeText(getApplicationContext(), "Eingeloggt", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
