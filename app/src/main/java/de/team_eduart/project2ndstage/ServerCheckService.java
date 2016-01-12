package de.team_eduart.project2ndstage;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ServerCheckService extends Service {
    public ServerCheckService() {
    }

    List<NameValuePair> params;
    TimerTask mTimerTask;


    @Override
    public void onCreate() {

        final SharedPreferences NetworkState = this.getSharedPreferences("NetworkState", MODE_PRIVATE);
        final String ServerIP = NetworkState.getString("ServerIP", null);
        final String Username = NetworkState.getString("Username", "Schueler");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(mTimerTask = new TimerTask() {
            public void run() {

                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userid", Username));
                params.add(new BasicNameValuePair("operation", "check"));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://"+ ServerIP +":8080/appsperr",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        if(json.getBoolean("res")){
                            startService(new Intent(ServerCheckService.this, AppLockService.class));
                        } else {
                            stopService(new Intent(ServerCheckService.this, AppLockService.class));
                        }
                        //Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "keine Serververbindung", Toast.LENGTH_LONG).show();
                }

                ServerRequest sr2 = new ServerRequest();
                JSONObject json2 = sr2.getJSON("http://"+ ServerIP +":8080/filesend",params);
                if(json2 != null){
                    try{
                        String jsonstr = json2.getString("response");
                        if(json2.getBoolean("res")){
                            Log.d("ServerCheck", "neue Datei gefunden");
                        } else {
                            Log.d("ServerCheck", "keine neue Datei gefunden");
                        }
                        //Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "keine Serververbindung", Toast.LENGTH_LONG).show();
                }
            }
        }, 0, 1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimerTask.cancel();
        Toast.makeText(getApplicationContext(), "Servercheck gestoppt", Toast.LENGTH_SHORT).show();

    }
}
