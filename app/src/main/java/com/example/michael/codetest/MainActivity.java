package com.example.michael.codetest;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends ListActivity {

    private static String URL = "http://de-coding-test.s3.amazonaws.com/books.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            pullData();
        } catch (IOException e) {
            openAlert("Check Internet connection");
        }
    }

    public void pullData() throws IOException {
        // pull data from the URL, it will be in JSON
        OkHttpClient client = ((MyApp)getApplication()).getClient();
        Request request = new Request.Builder().url(URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                openAlert("Connection failed");
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                Gson gson = new Gson();
                final Item[] items = gson.fromJson(data, Item[].class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateView(items);
                    }
                });
            }
        });
    }

    public void updateView(Item[] items) {
        for (Item item : items) {
            Log.d("ITEM", item.toString());
        }
        MyAdapter adapter = new MyAdapter(this, R.layout.entry, items);
        setListAdapter(adapter);
    }

    public void openAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setNegativeButton(getResources().getString(R.string.dismiss), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface d, int id) {
                // no need to do anything since it will dismiss itself
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
