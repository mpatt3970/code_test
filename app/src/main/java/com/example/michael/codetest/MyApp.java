package com.example.michael.codetest;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by michael on 1/8/15.
 */
public class MyApp extends Application {

    private OkHttpClient mClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mClient = new OkHttpClient();
    }

    public OkHttpClient getClient() {
        return mClient;
    }

}
