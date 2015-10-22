package de.team_eduart.project2ndstage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends ActionBarActivity {

    Button BtnLogin;
    EditText editTextUsername;
    EditText editTextPassword;

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
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = editTextUsername.getText().toString();
                String Password = editTextPassword.getText().toString();

                //Hardcoded als Test
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
            }
        });
    }

}
