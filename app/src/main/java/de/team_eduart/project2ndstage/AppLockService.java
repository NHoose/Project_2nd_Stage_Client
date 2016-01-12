package de.team_eduart.project2ndstage;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

                /*
                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                final ActivityManager.RunningTaskInfo top = am.getRunningTasks(1).get(0);
                final String packageName = top.topActivity.getPackageName();
                */

                String TopActivity;
                if (Build.VERSION.SDK_INT >= 21) {
                    TopActivity = getTopActivity();
                } else {
                    TopActivity = getTopActivityCompat();
                }

                Log.d("LockService", TopActivity);

                /*
                if (Arrays.asList(blacklistedApps).contains(TopActivity)) {

                    Toast.makeText(getApplicationContext(), "gesperrte App", Toast.LENGTH_SHORT).show();


                    Intent launchIntent = new Intent(getBaseContext(), LockedApp.class);
                    launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(launchIntent);


                }
                */
            }
        }, 0, 1000);

    }

    String getTopActivityCompat() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ActivityManager.RunningTaskInfo top = am.getRunningTasks(1).get(0);
        final String packageName = top.topActivity.getPackageName();
        return packageName;
    }

    String getTopActivity() {
        String TopActivityName;
        final int PROCESS_STATE_TOP = 2;
        ActivityManager.RunningAppProcessInfo currentInfo = null;
        Field field = null;
        try {
            field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
        } catch (Exception ignored) {
        }
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo app : appList) {
            if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && app.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) {
                Integer state = null;
                try {
                    state = field.getInt(app);
                } catch (Exception e) {
                }
                if (state != null && state == PROCESS_STATE_TOP) {
                    currentInfo = app;
                    break;
                }
            }
        }
        if (currentInfo != null) {
            TopActivityName = currentInfo.processName;
        } else {
            TopActivityName = "error";
        }
        return TopActivityName;
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