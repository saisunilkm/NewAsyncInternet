package com.thoughtfocus.newasyncinternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
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

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        MainActivity.myimg.setImageBitmap(bitmap);
    }
}
