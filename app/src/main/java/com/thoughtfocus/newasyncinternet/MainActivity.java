package com.thoughtfocus.newasyncinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DownloadImageTask downloadImageTask;
    ConnectInternetTask c1;
    static TextView mytext;
    static ImageView myimg;

    ConnectivityManager connectivityManager;
    NetworkInfo myinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytext = (TextView) findViewById(R.id.Myresult);
        myimg = (ImageView) findViewById(R.id.imageView);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        myinfo = connectivityManager.getActiveNetworkInfo();
    }

    public void doSomething(View view) {
        c1 = new ConnectInternetTask(this);
        c1.execute("http://www.google.com");
    }


    public void downloadimage(View view) {
        if (myinfo != null && myinfo.isConnected()){
            downloadImageTask = new DownloadImageTask();
            downloadImageTask.execute("https://image.shutterstock.com/image-photo/kiev-ukraine-june-7-2015-260nw-285031679.jpg");

        }else{
            Toast.makeText(this, "Internet is not connected", Toast.LENGTH_SHORT).show();
        }
    }
}