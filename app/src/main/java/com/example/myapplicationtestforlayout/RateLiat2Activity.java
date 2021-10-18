package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateLiat2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Handler handler;
    private static String TAG = "RateLiat2Activity";
    ArrayList<HashMap<String,String>> rate;
    private ListView ratelist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_liat2);
        ratelist2 = findViewById(R.id.ratelist2);
        ratelist2.setOnItemClickListener(this);
        ProgressBar progressBar = findViewById(R.id.progressBar);

//        ArrayList<HashMap<String,String>> listItems = new ArrayList<HashMap<String,String>>();
//        for(int i=0;i<10;i++){
//            HashMap<String,String> map = new HashMap<String,String>();
//            map.put("ItemTitle","Rate:"+i);
//            map.put("ItemDetail","Detail:"+i);
//            listItems.add(map);
//        }
//        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItems,
//                R.layout.liat_item,
//                new String[]{"ItemTitle","ItemDetail"},
//                new int[]{R.id.itemTitle,R.id.itemDetail}
//        );
//        ratelist2.setAdapter(listItemAdapter);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: received");
                if(msg.what == 8) {

                    rate = (ArrayList<HashMap<String,String>>) msg.obj;

                    MyAdapter adapter = new MyAdapter(RateLiat2Activity.this, android.R.layout.simple_list_item_1,rate);
                    ratelist2.setAdapter(adapter);

                    Log.i(TAG, "handleMessage: get rate");

                    progressBar.setVisibility(View.GONE);
                    ratelist2.setVisibility(View.VISIBLE);
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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Object itemAtPosition = ratelist2.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
        String titlestr = map.get("ItemTitle");
        String detailstr = map.get("ItemDetail");
        Log.i(TAG, "onItemClick: titlestr="+titlestr);
        Log.i(TAG, "onItemClick: detailstr="+detailstr);

        TextView title = (TextView) view.findViewById(R.id.itemTitle);
        TextView detail = (TextView) view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());
        Log.i(TAG, "onItemClick: title2="+title2);
        Log.i(TAG, "onItemClick: detail2="+detail2);

        Intent rate = new Intent(this,ListRateCalculateActivity.class);
        //Bundle bdl =new Budle();
        rate.putExtra("title",title2);
        rate.putExtra("detail",detail2);
        startActivityForResult(rate,1); //打开可返回窗口
    }
}