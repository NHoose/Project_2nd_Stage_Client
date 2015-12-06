package de.team_eduart.project2ndstage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InstalledAppsList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_apps_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBlank);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        loadApps();
        loadListView();
        addClickListener();
    }


    private PackageManager manager;
    private List<AppDetail> apps;
    private void loadApps(){
        manager = getPackageManager();
        apps = new ArrayList<AppDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }
    }


    private ListView list;
    private void loadListView(){
        list = (ListView)findViewById(R.id.installed_apps_list);

        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,
                R.layout.list_item,
                apps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                }

                ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
                appIcon.setImageDrawable(apps.get(position).icon);

                TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
                appLabel.setText(apps.get(position).label);

                TextView appName = (TextView)convertView.findViewById(R.id.item_app_name);
                appName.setText(apps.get(position).name);

                return convertView;
            }
        };

        list.setAdapter(adapter);
        registerForContextMenu(list);
    }


    private void addClickListener(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(pos).name.toString());
                InstalledAppsList.this.startActivity(i);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.applist_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        SharedPreferences SelectedHomeAppsPrefs = this.getSharedPreferences("SelectedHomeApps", MODE_PRIVATE);
        final SharedPreferences.Editor editor = SelectedHomeAppsPrefs.edit();

        //Apps gehen verloren ....

        switch (item.getItemId()) {
            case R.id.AddToHome:
                String selectedAppName =  apps.get(index).name.toString();
                if (SelectedHomeAppsPrefs.contains("SelectedMainApps")) {
                    Set<String> SelectedMainApps = new HashSet<String>(SelectedHomeAppsPrefs.getStringSet("SelectedMainApps", new HashSet<String>()));
                    SelectedMainApps.add(selectedAppName);
                    editor.putStringSet("SelectedMainApps", SelectedMainApps);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), selectedAppName + " hinzugefügt", Toast.LENGTH_SHORT).show();
                } else {
                    Set<String> SelectedMainApps = new HashSet<String>();
                    SelectedMainApps.add(selectedAppName);
                    editor.putStringSet("SelectedMainApps", SelectedMainApps);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Prefs erstellt und " + selectedAppName + " hinzugefügt", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
