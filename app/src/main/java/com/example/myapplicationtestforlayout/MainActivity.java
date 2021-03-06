package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static float dollarrate = 7.6f;
    public static float poundrate = 8.7f;
    public static float japanrate = 22.0f;
    public static final String TAG="MainActivity";

    Handler handler;

    EditText input;
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneychange);
        input = findViewById(R.id.money);
        output = findViewById(R.id.changedmoney);

        SharedPreferences sp = getSharedPreferences("myrte", Activity.MODE_PRIVATE);
        dollarrate = sp.getFloat("dollarrate",dollarrate);
        poundrate = sp.getFloat("poundrate",poundrate);
        japanrate = sp.getFloat("japanrate",japanrate);
        Log.i(TAG, "onCreate: dollarrate"+dollarrate);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: received");
                if(msg.what == 6) {
                    Bundle bdl = (Bundle) msg.obj;
                    dollarrate = bdl.getFloat("dollar");
                    japanrate = bdl.getFloat("japan");
                    poundrate = bdl.getFloat("pound");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putFloat("dollarrate",dollarrate);
                    editor.putFloat("poundrate",poundrate);
                    editor.putFloat("japanrate",japanrate);
                    editor.apply();
                    Toast.makeText(MainActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

        //????????????
        Boolean isTodayFirstStartApp =  isTodayFirstStartApp(this);
        if(isTodayFirstStartApp) {
            MainActivity7 td = new MainActivity7();
            td.setHandler(handler);
            Thread t = new Thread(td);
            t.start();
        }
    }

    public void plus(View plus) {
        String inp = input.getText().toString();
        if(inp.length()>0) {
            double x = Double.parseDouble(inp);
            if (plus.getId() == R.id.dollar) {
                x = x * dollarrate;
                output.setText(String.valueOf(x));
            } else if (plus.getId() == R.id.pound) {
                x = x * poundrate;
                output.setText(String.valueOf(x));
            } else if (plus.getId() == R.id.japan) {
                x = x * japanrate;
                output.setText(String.valueOf(x));
            }
        }else{
            output.setText("???????????????");
            Toast.makeText(MainActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
        }
    }

    public void edit(View edit){
        openconfig();
    }

    private void openconfig(){
        Intent config = new Intent(this,MainActivity2.class);
        //Bundle bdl =new Budle();
        config.putExtra("dollarrate",dollarrate);
        config.putExtra("poundrate",poundrate);
        config.putExtra("japanrate",japanrate);
        Log.i(TAG, "openconfig: dollarrate="+dollarrate);
        startActivityForResult(config,1); //?????????????????????
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode==1 && resultCode==3){
            dollarrate = data.getFloatExtra("dollarrate",0.0f);
            poundrate = data.getFloatExtra("poundrate",0.0f);
            japanrate = data.getFloatExtra("japanrate",0.0f);
            Log.i(TAG, "onActivityResult: dollarrate="+dollarrate);
        }
        super.onActivityResult(requestCode,resultCode,data);

        SharedPreferences sp = getSharedPreferences("myrte",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollarrate",dollarrate);
        editor.putFloat("poundrate",poundrate);
        editor.putFloat("japanrate",japanrate);
        editor.apply();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.setting){
            openconfig();
        }
        return super.onOptionsItemSelected(item);
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

    public static boolean isTodayFirstStartApp(Context context) {
        try {
            SharedPreferences preferences = context.getSharedPreferences("NB_TODAY_FIRST_START_APP", context.MODE_PRIVATE);
            String saveTime = preferences.getString("startAppTime", "2020-01-08");
            String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            if (!TextUtils.isEmpty(todayTime) && !TextUtils.isEmpty(saveTime)) {
                if(!saveTime.equals(todayTime)) {
                    preferences.edit().putString("startAppTime", todayTime).commit();
                    return true;
                }
            }

        }catch (Exception e){
            Log.i(TAG, "???????????????????????????APP,???????????????"+e.toString());
            return true;
        }
        return  false;

    }


}

