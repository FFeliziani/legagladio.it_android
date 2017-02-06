package com.federicofeliziani.legagladio.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.federicofeliziani.legagladio.entities.Coach;
import com.federicofeliziani.legagladio.entities.Team;
import com.federicofeliziani.legagladio.utils.UrlConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static com.federicofeliziani.legagladio.utils.Constants.BROADCAST_ACTION;
import static com.federicofeliziani.legagladio.utils.Constants.DOWNLOAD_URL;
import static com.federicofeliziani.legagladio.utils.Constants.PENDING_RESULT_EXTRA;
import static com.federicofeliziani.legagladio.utils.Constants.URL_EXTRA;

/**
 * Created by f3l1x on 10/18/2016.
 */

public class DownloadIntentService extends IntentService {
    private static final String TAG = DownloadIntentService.class.getSimpleName();

    public DownloadIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT_EXTRA);
        try {
            String html = "";

            URL url = new URL(intent.getStringExtra(URL_EXTRA));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            in.close();

            Gson g = new GsonBuilder().create();

            Intent result = new Intent(BROADCAST_ACTION);
            Type listType = null;
            switch (url.toString()) {
                case UrlConstants.COACH_LIST:
                    listType = new TypeToken<ArrayList<Coach>>() {}.getType();
                    ArrayList<Coach> coachList = g.fromJson(str.toString(), listType);
                    result.putParcelableArrayListExtra(URL_EXTRA, coachList);
                    break;
                case UrlConstants.TEAM_LIST:
                    listType = new TypeToken<ArrayList<Team>>() {}.getType();
                    ArrayList<Team> teamList = g.fromJson(str.toString(), listType);
                    result.putParcelableArrayListExtra(URL_EXTRA, teamList);
                    break;
            }

            result.putExtra(DOWNLOAD_URL, url);
            sendBroadcast(result);
            //reply.send(this, 0, result);
        } catch (Exception ex) {
            Log.i(TAG, ex.getStackTrace().toString(), ex);
        }
    }
}
