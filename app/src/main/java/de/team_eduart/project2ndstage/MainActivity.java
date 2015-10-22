package de.team_eduart.project2ndstage;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private CharSequence mTitle;
    private NavigationView mNavigationView;

    Button HomeButton;
    Button CalButton;
    Button MailButton;
    Button NotificationsButton;

    String CurrentlySelectedFragment;

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
                    case R.id.NavItem_Cal:
                        mDrawerLayout.closeDrawers();
                        SwitchToCal();
                        return true;
                    case R.id.NavItem_Mail:
                        mDrawerLayout.closeDrawers();
                        SwitchToMail();
                        return true;
                    case R.id.NavItem_Notifications:
                        mDrawerLayout.closeDrawers();
                        SwitchToNotifications();
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
                        mDrawerLayout.closeDrawers();
                        Logout();
                        return true;
                    default:
                        return true;
                }
            }
        });

        // swap Fragment_container with HomeFragment and mark home as selected
        SwitchToHome();

        //check current LoginState and change NavDrawer
        refreshNetworkState();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        CalButton = (Button) findViewById(R.id.BtnCal);
        MailButton = (Button) findViewById(R.id.BtnMail);
        NotificationsButton = (Button) findViewById(R.id.BtnNotifications);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToHome();
            }
        });

        CalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToCal();
            }
        });

        MailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToMail();
            }
        });

        NotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToNotifications();
            }
        });
    }

    private void SwitchToHome() {
        //Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(0).setChecked(true);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_white);

        CalButton = (Button) findViewById(R.id.BtnCal);
        CalButton.setBackgroundResource(R.drawable.calendar_black);

        MailButton = (Button) findViewById(R.id.BtnMail);
        MailButton.setBackgroundResource(R.drawable.mail_black);

        NotificationsButton = (Button) findViewById(R.id.BtnNotifications);
        NotificationsButton.setBackgroundResource(R.drawable.news_black);

        CurrentlySelectedFragment = "Home";
    }

    private void SwitchToCal() {
        //Toast.makeText(getApplicationContext(), "Kalender", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(1).setChecked(true);

        CalendarFragment kalFragment = new CalendarFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, kalFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_black);

        CalButton = (Button) findViewById(R.id.BtnCal);
        CalButton.setBackgroundResource(R.drawable.calendar_white);

        MailButton = (Button) findViewById(R.id.BtnMail);
        MailButton.setBackgroundResource(R.drawable.mail_black);

        NotificationsButton = (Button) findViewById(R.id.BtnNotifications);
        NotificationsButton.setBackgroundResource(R.drawable.news_black);

        CurrentlySelectedFragment = "Calendar";
    }

    private void SwitchToMail() {
        //Toast.makeText(getApplicationContext(), "Postfach", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(2).setChecked(true);

        MailFragment postFragment = new MailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, postFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_black);

        CalButton = (Button) findViewById(R.id.BtnCal);
        CalButton.setBackgroundResource(R.drawable.calendar_black);

        MailButton = (Button) findViewById(R.id.BtnMail);
        MailButton.setBackgroundResource(R.drawable.mail_white);

        NotificationsButton = (Button) findViewById(R.id.BtnNotifications);
        NotificationsButton.setBackgroundResource(R.drawable.news_black);

        CurrentlySelectedFragment = "Mail";
    }

    private void SwitchToNotifications() {
        //Toast.makeText(getApplicationContext(), "Benachrichtigungen", Toast.LENGTH_SHORT).show();

        Menu menu = mNavigationView.getMenu();
        menu.getItem(3).setChecked(true);

        NotificationsFragment newsFragment = new NotificationsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newsFragment)
                .commit();

        HomeButton = (Button) findViewById(R.id.BtnHome);
        HomeButton.setBackgroundResource(R.drawable.home_black);

        CalButton = (Button) findViewById(R.id.BtnCal);
        CalButton.setBackgroundResource(R.drawable.calendar_black);

        MailButton = (Button) findViewById(R.id.BtnMail);
        MailButton.setBackgroundResource(R.drawable.mail_black);

        NotificationsButton = (Button) findViewById(R.id.BtnNotifications);
        NotificationsButton.setBackgroundResource(R.drawable.news_white);

        CurrentlySelectedFragment = "Notifications";
    }

    private void Logout() {
        // eigentlich erst wenn erfolgreich ausgeloggt
        Toast.makeText(getApplicationContext(), "Ausgeloggt", Toast.LENGTH_SHORT).show();

        SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);
        final SharedPreferences.Editor editor = NetworkState.edit();

        editor.putBoolean("LoggedIn", false);
        editor.apply();

        refreshNetworkState();
    }

    private void Login() {
        Intent openLogin = new Intent(MainActivity.this, Login.class);
        startActivity(openLogin);
    }

    private void refreshNetworkState() {
        SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);

        Boolean LoggedIn = NetworkState.getBoolean("LoggedIn", false);
        String Username = NetworkState.getString("Username", "Max Musterschüler");

        TextView NavDrawerUsername = (TextView)findViewById(R.id.NavDrawerUsername);

        if (LoggedIn) {
            Menu menu = mNavigationView.getMenu();
            menu.clear();
            mNavigationView.inflateMenu(R.menu.navdrawer_loggedin);
            NavDrawerUsername.setText(Username);
            reselectLastFragment();
        } else {
            Menu menu = mNavigationView.getMenu();
            menu.clear();
            mNavigationView.inflateMenu(R.menu.navdrawer_loggedout);
            NavDrawerUsername.setText("Max Musterschüler");
            reselectLastFragment();
        }
    }

    private void reselectLastFragment() {
        Menu menu = mNavigationView.getMenu();

        //Toast.makeText(getApplicationContext(), CurrentlySelectedFragment, Toast.LENGTH_SHORT).show();

        if (CurrentlySelectedFragment.equals("Home")) {menu.getItem(0).setChecked(true);}
        else if (CurrentlySelectedFragment.equals("Calendar")) {menu.getItem(1).setChecked(true);}
        else if (CurrentlySelectedFragment.equals("Mail")) {menu.getItem(2).setChecked(true);}
        else if (CurrentlySelectedFragment.equals("Notifications")) {menu.getItem(3).setChecked(true);}

    }

    @Override
    public void onResume() {
        super.onResume();

        refreshNetworkState();
        SwitchToHome();
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
