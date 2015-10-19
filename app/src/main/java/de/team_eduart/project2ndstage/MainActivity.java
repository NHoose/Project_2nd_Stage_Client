package de.team_eduart.project2ndstage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private CharSequence mTitle;
    private NavigationView mNavigationView;

    Button HomeButton;
    Button KalButton;
    Button PostButton;
    Button NewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.NavView);

        toolbar.setNavigationIcon(R.drawable.ic_drawer_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.NavItem_Home:
                        mDrawerLayout.closeDrawers();
                        SwitchToHome();
                        return true;
                    case R.id.NavItem_Kal:
                        mDrawerLayout.closeDrawers();
                        SwitchToKal();
                        return true;
                    case R.id.NavItem_Post:
                        mDrawerLayout.closeDrawers();
                        SwitchToPost();
                        return true;
                    case R.id.NavItem_News:
                        mDrawerLayout.closeDrawers();
                        SwitchToNews();
                        return true;
                    case R.id.NavItem_Settings:
                        mDrawerLayout.closeDrawers();
                        Intent openSettings = new Intent(MainActivity.this, Settings.class);
                        startActivity(openSettings);
                        return true;
                    case R.id.NavItem_Login:
                        mDrawerLayout.closeDrawers();
                        Login();
                        return true;
                    case R.id.NavItem_Logout:
                        Logout();
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        return true;
                }
            }
        });

        // swap Fragment_container with HomeFragment and mark home as selected
        SwitchToHome();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        KalButton = (Button) findViewById(R.id.BtnKal);
        PostButton = (Button) findViewById(R.id.BtnPost);
        NewsButton = (Button) findViewById(R.id.BtnNews);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToHome();
            }
        });

        KalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToKal();
            }
        });

        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToPost();
            }
        });

        NewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToNews();
            }
        });
    }

    private void SwitchToHome() {
        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(0).setChecked(true);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_white);

        KalButton = (Button) findViewById(R.id.BtnKal);
        KalButton.setBackgroundResource(R.drawable.calendar_black);

        PostButton = (Button) findViewById(R.id.BtnPost);
        PostButton.setBackgroundResource(R.drawable.mail_black);

        NewsButton = (Button) findViewById(R.id.BtnNews);
        NewsButton.setBackgroundResource(R.drawable.news_black);
    }

    private void SwitchToKal() {
        Toast.makeText(getApplicationContext(), "Kalender", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(1).setChecked(true);

        KalenderFragment kalFragment = new KalenderFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, kalFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_black);

        KalButton = (Button) findViewById(R.id.BtnKal);
        KalButton.setBackgroundResource(R.drawable.calendar_white);

        PostButton = (Button) findViewById(R.id.BtnPost);
        PostButton.setBackgroundResource(R.drawable.mail_black);

        NewsButton = (Button) findViewById(R.id.BtnNews);
        NewsButton.setBackgroundResource(R.drawable.news_black);
    }

    private void SwitchToPost() {
        Toast.makeText(getApplicationContext(), "Postfach", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(2).setChecked(true);

        PostFragment postFragment = new PostFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, postFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_black);

        KalButton = (Button) findViewById(R.id.BtnKal);
        KalButton.setBackgroundResource(R.drawable.calendar_black);

        PostButton = (Button) findViewById(R.id.BtnPost);
        PostButton.setBackgroundResource(R.drawable.mail_white);

        NewsButton = (Button) findViewById(R.id.BtnNews);
        NewsButton.setBackgroundResource(R.drawable.news_black);
    }

    private void SwitchToNews() {
        Toast.makeText(getApplicationContext(), "Benachrichtigungen", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(3).setChecked(true);

        NewsFragment newsFragment = new NewsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newsFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_black);

        KalButton = (Button) findViewById(R.id.BtnKal);
        KalButton.setBackgroundResource(R.drawable.calendar_black);

        PostButton = (Button) findViewById(R.id.BtnPost);
        PostButton.setBackgroundResource(R.drawable.mail_black);

        NewsButton = (Button) findViewById(R.id.BtnNews);
        NewsButton.setBackgroundResource(R.drawable.news_white);
    }

    private void Logout() {
        Toast.makeText(getApplicationContext(), "Ausgeloggt", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.clear();
        mNavigationView.inflateMenu(R.menu.navdrawer_loggedout);
    }

    private void Login() {
        Intent openLogin = new Intent(MainActivity.this, Login.class);
        startActivity(openLogin);

        Menu menu = mNavigationView.getMenu();
        menu.clear();
        mNavigationView.inflateMenu(R.menu.navdrawer_loggedin);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
