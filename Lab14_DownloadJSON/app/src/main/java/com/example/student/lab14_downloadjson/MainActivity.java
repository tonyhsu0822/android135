package com.example.student.lab14_downloadjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private TextView m_tv;
    private AsyncDownloadTask mDownloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initJson();
    }

    private void initView() {
        m_tv = findViewById(R.id.tv);
    }

    private void initJson() {
        String baseUrl = "https://drive.google.com/uc?export=download&id=";
        String idUrl = "1HQK6vH06RLtFRUWKI06c6LBasNZwDLXF";

//        Thread task = new DownloadTask(baseUrl + idUrl);
//        task.start();
        mDownloadTask = new AsyncDownloadTask();
        mDownloadTask.execute(baseUrl + idUrl);
    }

    @Override
    protected void onStop() {
        if(mDownloadTask != null) {
            mDownloadTask.cancel(true);
            Log.d("onStop", "canceled the download task");
        }
        super.onStop();
    }

    private class AsyncDownloadTask extends AsyncTask<String, Void, CharSequence> {

        @Override
        protected CharSequence doInBackground(String... strings) {
            URL targetUrl;
            StringBuilder sb = new StringBuilder();
            try {
                targetUrl = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.getClass().getName();
            }
            try(InputStream is = targetUrl.openStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader reader = new BufferedReader(isr)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    Log.d("AsyncDownloadTask", "read line: " + line);
                    sb.append(line);
                    if(isCancelled()) {
                        Log.d("AsyncDownloadTask", "task was canceled");
                        return sb;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.getClass().getName();
            }
            return sb;
        }

        @Override
        protected void onPostExecute(CharSequence cs) {
            Log.d("AsyncDownloadTask", "Download completed");
            m_tv.setText(cs);
        }
    }

    private class DownloadTask extends Thread {

        URL targetUrl;

        DownloadTask(String url) {
            try {
                targetUrl = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try(InputStream is = targetUrl.openStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader reader = new BufferedReader(isr)) {

                String line;
                final StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    Log.d("DownloadTask", "read line: " + line);
                    sb.append(line);
                }
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        m_tv.setText(sb.toString());
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
