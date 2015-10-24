package de.team_eduart.project2ndstage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends ActionBarActivity {

    Button BtnSetServerIP;
    EditText editTextServerIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBlank);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        editTextServerIP = (EditText) findViewById(R.id.editTextServerIP);
        BtnSetServerIP = (Button) findViewById(R.id.BtnSetServerIP);

        SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);
        final SharedPreferences.Editor editor = NetworkState.edit();

        BtnSetServerIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ServerIP = editTextServerIP.getText().toString();

                editor.putString("ServerIP", ServerIP);
                editor.apply();
                finish();
            }
        });
    }

}
