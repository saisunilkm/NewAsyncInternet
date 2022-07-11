package com.thoughtfocus.newasyncinternet;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectInternetTask extends AsyncTask<String,Void,String> {
    Context ctx;

    public ConnectInternetTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream is;

        try {
            URL myURL = new URL(s1);
            HttpURLConnection myCom = (HttpURLConnection)myURL.openConnection();
            myCom.setReadTimeout(10000);
            myCom.setConnectTimeout(20000);
            myCom.setRequestMethod("GET");
            myCom.connect();

            is = myCom.getInputStream();
            BufferedReader myBuf = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = myBuf.readLine()) != null){
                sb.append(line +" \n");
            }
            myBuf.close();
            is.close();
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.mytext.setText(s);
    }
}
