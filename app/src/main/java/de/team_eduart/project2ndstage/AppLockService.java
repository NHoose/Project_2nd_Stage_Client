package de.team_eduart.project2ndstage;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class AppLockService extends Service {
    public AppLockService() {
    }

    String[] blacklistedApps = {"com.android.settings", "com.android.dialer"};

    TimerTask mTimerTask;


    @Override
    public void onCreate() {

        Toast.makeText(getApplicationContext(), "Apps gesperrt!", Toast.LENGTH_SHORT).show();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(mTimerTask = new TimerTask() {
            public void run() {

                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                final ActivityManager.RunningTaskInfo top = am.getRunningTasks(1).get(0);
                final String packageName = top.topActivity.getPackageName();

                if (Arrays.asList(blacklistedApps).contains(packageName)) {

                    Intent launchIntent = new Intent(getBaseContext(), LockedApp.class);
                    launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(launchIntent);

                }

            }
        }, 0, 1000);

    }








    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimerTask.cancel();
        Toast.makeText(getApplicationContext(), "Apps entsperrt!", Toast.LENGTH_SHORT).show();

    }


}