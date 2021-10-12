package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends AppCompatActivity{
    Handler handler;
    private static String TAG = "RateListActivity";
    List<String> rate = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        ListView listview = findViewById(R.id.rate_list);

        List<String> list1 = new ArrayList<String>();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: received");
                if(msg.what == 7) {
                    for(int i = 1;i<100;i++){
                        list1.add("item"+i);
                    }
                    rate = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1,rate);
                    listview.setAdapter(adapter);
                    Log.i(TAG, "handleMessage: get rate");
                    Toast.makeText(RateListActivity.this,"数据已更新",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

        MainActivity7 td = new MainActivity7();
        td.setHandler(handler);
        Thread t = new Thread(td);
        t.start();
        Log.i(TAG, "onCreate: open New Thread");
    }
}