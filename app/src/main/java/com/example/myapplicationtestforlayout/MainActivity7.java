package com.example.myapplicationtestforlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity7 implements Runnable {

    private static final String TAG = "MainActivity7";
    private Handler handler;
    List<String> rate = new ArrayList<String>();

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: Thread");

        //访问网络资源
//        URL url = null;
//        url = new URL("http:/www.usd-cny.com/bankofchina.htm");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//            String html = inputStream2String(in);
//            Log.i(TAG, "run: html="+html);
        Bundle bundle = new Bundle();
        try {
            String url = " https://www.boc.cn/sourcedb/whpj/";//http:/www.usd-cny.com/bankofchina.htm
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.getElementsByTag("table");
            Element table = tables.get(1);
            Elements tds = table.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i +=8) {
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);

                String str1 = td1.text();
                String str2 = td2.text();
                rate.add(str1 + "==" + str2);
                Log.i(TAG, "run: " + str1 + "==" + str2);
            }

//            Log.i(TAG, "run:title=" + doc.title());
//
//            Elements h4s = doc.getElementsByTag("h4");
//            for (Element h4 : h4s) {
//                Log.i(TAG, "run: h4=" + h4.text());
//            }
//
//            Elements tables = doc.getElementsByTag("table");
//            Element table1 = tables.first(); //table.get(0)
//            //Log.i(TAG, "run: table1="+table1);
//
//            Elements tds = table1.getElementsByTag("td");
//            //Log.i(TAG, "run: td="+td);
//            for (int i = 0; i < tds.size(); i += 6) {
//                Element td1 = tds.get(i);
//                Element td2 = tds.get(i + 5);
//
//                String str1 = td1.text();
//                String str2 = td2.text();
//
//                rate.add(str1+"=="+str2);
//                Log.i(TAG, "run: "+ str1 + "==" + str2);
//                if (str1.equals("美元")) {
//                    bundle.putFloat("dollar", 100f / Float.parseFloat(str2));
//                    Log.i(TAG, "run: " + str1 + "==" + str2);
//                } else if (str1.equals("日元")) {
//                    bundle.putFloat("japan", 100f / Float.parseFloat(str2));
//                    Log.i(TAG, "run: " + str1 + "==" + str2);
//                } else if (str1.equals("英镑")) {
//                    bundle.putFloat("pound", 100f / Float.parseFloat(str2));
//                    Log.i(TAG, "run: " + str1 + "==" + str2);
//                }
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //回主线程
        Message msg = handler.obtainMessage(6, bundle);
        Message msg1 = handler.obtainMessage(7,rate);
        handler.sendMessage(msg);
        handler.sendMessage(msg1);
        Log.i(TAG, "run: bundle已发送");
        Log.i(TAG, "run: rate已发送");

    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}