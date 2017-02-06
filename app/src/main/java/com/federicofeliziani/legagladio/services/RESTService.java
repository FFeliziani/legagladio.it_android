package com.federicofeliziani.legagladio.services;

import android.app.Activity;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by f3l1x on 10/14/2016.
 */

public class RESTService extends Activity {

    public static String getUrl(String url) throws Exception
    {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)u.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(10000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return (String)conn.getContent();
    }

}
