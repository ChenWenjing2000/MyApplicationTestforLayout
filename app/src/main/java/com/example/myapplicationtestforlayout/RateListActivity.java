package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateListActivity extends AppCompatActivity{
    Handler handler;
    private static String TAG = "RateListActivity";
    List<String> rate = new ArrayList<String>();
    private String logDate = "";
    private final String DATE_SP_KEY = "lastRateDateStr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
        logDate = sp.getString(DATE_SP_KEY, "");
        Log.i("List","lastRateDateStr=" + logDate);
        setContentView(R.layout.activity_rate_list);
        ListView listview = findViewById(R.id.rate_list);

        ProgressBar progressBar = findViewById(R.id.progressBar);
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

                    progressBar.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };

        Test td = new Test();
        td.setHandler(handler);
        Thread t = new Thread(td);
        t.start();
        Log.i(TAG, "onCreate: open New Thread");
    }

    public static class DBHelper extends SQLiteOpenHelper {

        private static final int VERSION = 1;
        private static final String DB_NAME = "myrate.db";
        public static final String TB_NAME = "tb_rates";


        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }
        public DBHelper(Context context) {
            super(context,DB_NAME,null,VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+TB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURNAME TEXT,CURRATE TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
        }

    }

    public class Test implements Runnable{

        Handler handler;

        @Override
        public void run() {
            Log.i("List","run...");
            List<String> retList = new ArrayList<String>();
            Message msg = handler.obtainMessage();
            String curDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
            Log.i("run","curDateStr:" + curDateStr + " logDate:" + logDate);
            if(curDateStr.equals(logDate)){
                //如果相等，则不从网络中获取数据
                Log.i("run","日期相等，从数据库中获取数据");
                DBManager dbManager = new DBManager(RateListActivity.this);
                for(RateItem rateItem : dbManager.listAll()){
                    retList.add(rateItem.getCurName() + "=>" + rateItem.getCurRate());
                }
            }else{
                Log.i("run","日期相等，从网络中获取在线数据");
                //获取网络数据
                try {
                    List<RateItem> rateList = new ArrayList<RateItem>();
                    URL url = new URL("http://www.usd-cny.com/bankofchina.htm");
                    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                    InputStream in = httpConn.getInputStream();
                    String retStr = inputStream2String(in);

                    //Log.i("WWW","retStr:" + retStr);
                    //需要对获得的html字串进行解析，提取相应的汇率数据...

                    Document doc = Jsoup.parse(retStr);
                    Elements tables  = doc.getElementsByTag("table");

                    Element retTable = tables.get(5);
                    Elements tds = retTable.getElementsByTag("td");
                    int tdSize = tds.size();
                    for(int i=0;i<tdSize;i+=8){
                        Element td1 = tds.get(i);
                        Element td2 = tds.get(i+5);
                        //Log.i("www","td:" + td1.text() + "->" + td2.text());
                        float val = Float.parseFloat(td2.text());
                        val = 100/val;
                        retList.add(td1.text() + "->" + val);

                        RateItem rateItem = new RateItem(td1.text(),td2.text());
                        rateList.add(rateItem);
                    }
                    DBManager dbManager = new DBManager(RateListActivity.this);
                    dbManager.deleteAll();
                    Log.i("db","删除所有记录");
                    dbManager.addAll(rateList);
                    Log.i("db","添加新记录集");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //更新记录日期
                SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString(DATE_SP_KEY, curDateStr);
                edit.commit();
                Log.i("run","更新日期结束：" + curDateStr);
            }

            msg.obj = retList;
            msg.what = 7;
            handler.sendMessage(msg);
        }
        public void setHandler(Handler handler) {
            this.handler = handler;
        }

        private String inputStream2String(InputStream inputStream) throws IOException {
            final int bufferSize =1024;
            final char[] buffer = new char[bufferSize];
            final StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(inputStream,"gb2312");
            while(true){
                int rsz = in.read(buffer,0,buffer.length);
                if(rsz<0)
                    break;
                out.append(buffer,0,rsz);
            }
            return out.toString();
        }
    }
}