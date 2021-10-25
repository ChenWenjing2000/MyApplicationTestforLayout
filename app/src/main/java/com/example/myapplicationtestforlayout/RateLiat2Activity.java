package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateLiat2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener ,AdapterView.OnItemLongClickListener {
    Handler handler;
    private static String TAG = "RateLiat2Activity";
    ArrayList<Item> rate;
    GridView ratelist2;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_liat2);
        ratelist2 = findViewById(R.id.ratelist2);
        ratelist2.setOnItemClickListener(this);
        ratelist2.setOnItemLongClickListener(this);
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

                    rate = (ArrayList<Item>) msg.obj;

                    adapter = new MyAdapter(RateLiat2Activity.this, android.R.layout.simple_list_item_1,rate);
                    ratelist2.setAdapter(adapter);

                    ratelist2.setEmptyView(findViewById(R.id.no_data));

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
        Item item = (Item) itemAtPosition;
        String titlestr = item.getCname();
        String detailstr = item.getCval();
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
        startActivity(rate); //打开可返回窗口
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Log.i(TAG, "onPointerCaptureChanged: 长按操作");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("请确认是否删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG, "onClick: 对话框事件处理");
                        rate.remove(ratelist2.getItemAtPosition(position));
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("否",null);
        builder.create().show();
        return true;
    }
}