package com.rajchandan.codingchallenge;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.out;

public class APIHandler
{
    private static final String TAG = APIHandler.class.getSimpleName();
    public static String response;
    public static boolean isdoneconn = false;
    private String APIUrl = "https://reqres.in/api/users";

    public APIHandler()
    {
    }

    public void makeAPICalls()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(APIUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    //read the response
                    InputStream in = conn.getInputStream();
                    response = convertStreamToString(in);
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
                isdoneconn = true;
            }
        });
        thread.start();
    }

    public void pushAPICalls(final ArrayList<HashMap<String, String>> data)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(APIUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                    conn.setRequestProperty("ACCEPT-LANGUAGE", "en-US");
                    conn.setDoOutput(true);
                    conn.connect();
                    //send the response
                    OutputStream out = new BufferedOutputStream(conn.getOutputStream());

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                    for(int i = 0; i < 3; i++)
                    {
                        writer.write(String.valueOf(data.get(i)));
                    }
                    writer.flush();
                    writer.close();
                    out.close();

                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
                isdoneconn = true;
            }
        });
        thread.start();
    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
