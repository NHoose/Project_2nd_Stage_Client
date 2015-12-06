package de.team_eduart.project2ndstage;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class HomeFragment extends Fragment {

    Button AppListButton;

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        AppListButton = (Button) rootView.findViewById(R.id.BtnAppList);

        AppListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAppList = new Intent(getActivity(), InstalledAppsList.class);
                startActivity(openAppList);

            }
        });

        loadApps(rootView);

        return rootView;
    }

    private PackageManager manager;
    private List<AppDetail> apps;
    private void loadApps(View rootView){
        manager = this.getActivity().getPackageManager();
        apps = new ArrayList<AppDetail>();

        SharedPreferences SelectedHomeAppsPrefs = this.getActivity().getSharedPreferences("SelectedHomeApps", Context.MODE_PRIVATE);
        Set<String> SelectedMainApps = SelectedHomeAppsPrefs.getStringSet("SelectedMainApps", null);

        if (SelectedHomeAppsPrefs.contains("SelectedMainApps")) {



            for(String pkgName:SelectedMainApps){
                Intent intent = new Intent();
                intent.setPackage(pkgName);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> availableActivities = manager.queryIntentActivities(intent, 0);
                for(ResolveInfo ri:availableActivities){
                    AppDetail app = new AppDetail();
                    app.label = ri.loadLabel(manager);
                    app.name = ri.activityInfo.packageName;
                    app.icon = ri.activityInfo.loadIcon(manager);
                    apps.add(app);
                }
            }
            loadListView(rootView);
            addClickListener();
        }
    }


    private ListView list;
    private void loadListView(View rootView){
        list = (ListView)rootView.findViewById(R.id.home_apps_list);

        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(getActivity().getApplicationContext(), R.layout.list_item, apps) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if(convertView == null){
                            convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item, null);
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
                HomeFragment.this.startActivity(i);
            }
        });
    }

}
