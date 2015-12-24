package de.team_eduart.project2ndstage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class LockedApp extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked_app);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("App gesperrt!");

        Button BackToHome = (Button) findViewById(R.id.BtnBackToHome);

        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLauncher = new Intent(LockedApp.this, MainActivity.class);
                startActivity(toLauncher);
            }
        });

    }


    @Override
    public void onBackPressed() {
    }
}


