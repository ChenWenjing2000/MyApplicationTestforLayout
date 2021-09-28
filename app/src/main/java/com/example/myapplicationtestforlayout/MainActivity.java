package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity implements Runnable{
    public static float dollarrate = 7.6f;
    public static float poundrate = 8.7f;
    public static float japanrate = 22.0f;
    public static final String TAG="MainActivity";

    Handler handler;
//    View view;
//    TextView output;
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        output = findViewById(R.id.textView2);
//        LocalDate localDate = LocalDate.now();
//        output.setText(localDate + "plus" + LocalTime.now());
//        Log.i("111", "on");
//
//        Button btn = findViewById(R.id.button);
//
////        btn.setOnClickListener(this); //窗口对象this
////
////        //匿名类监听
////        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                output.setText("BYE");
////            }
////        });
////    }
////
////    @Override
////    public void onClick(View view) {
////        String time = output.getText().toString();
////        output.setText("HI");
////    }
//
//    }
//
//    public void onClick(View view) {
//        String time = output.getText().toString();
//        output.setText("HI");
//    }


//    EditText input;
//    TextView output;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.temperature);
//        input = findViewById(R.id.textView4);
//
//        Button btn = findViewById(R.id.button2);
//
//        //匿名类监听
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double tem1 = Double.parseDouble(input.getText().toString());
//                double tem2 = (tem1 * 9 / 5) + 32;
//                output = findViewById(R.id.textView5);
//                output.setText(Double.toString(tem2));
//            }
//        });
//
//    }


//    RadioButton male;
//    RadioButton female;
//    TextView Result;
//    EditText text1;
//    EditText text2;
//    Button btn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bmi);
//        setViews();
//    }
//    public void setViews() {
//        male = (RadioButton) findViewById(R.id.male);
//        female = (RadioButton) findViewById(R.id.female);//判断男女
//        Result = (TextView) findViewById(R.id.Result);
//        text1 = (EditText)findViewById(R.id.height);
//        text2 = (EditText)findViewById(R.id.weight);
//
//        btn = (Button)findViewById(R.id.btn);
//        //btn.setOnClickListener(this);
//
//    }
//
//    public void onClick(View v) {
//        double x = Double.parseDouble(text1.getText().toString());//身高
//        double y = Double.parseDouble(text2.getText().toString());//体重//体重除以身高的平方
//        double res;
//        String str = "Your BMI is";
//        if(x<=0 || y<=0) {
//            Result.setText("值异常，不计算");
//            return ;
//        }
//        x = x/100;
//        res = y / (x*x);
//        String str1 = String.format("%.2f",res);
//        str = str + str1;
//        if(female.isChecked()) {
//            str += "体型:";
//            if (res < 19)
//                str += "过轻";
//            else if (res < 24)
//                str += "适中";
//            else if (res < 30)
//                str += "超重";
//            else
//                str += "肥胖";
//            Result.setText(str);
//        }else{
//            str += "体型:";
//            if (res < 18)
//                str += "过轻";
//            else if (res < 24)
//                str += "适中";
//            else if (res < 28)
//                str += "超重";
//            else
//                str += "肥胖";
//            Result.setText(str);
//        }

//
//    TextView sum2;
//    TextView sum;
//    int ss =0;
//    int sss =0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.countmark);
//        sum = findViewById(R.id.sum);
//        sum2 = findViewById(R.id.sum2);
//    }

//    public void plus(View plus) {
//        if(plus.getId() == R.id.plus1){
//            ss = ss+1;
//            sum.setText(String.valueOf(ss));
//        }else if(plus.getId() == R.id.plus2){
//            ss = ss+2;
//            sum.setText(String.valueOf(ss));
//        }else if(plus.getId() == R.id.plus3){
//            ss = ss+3;
//            sum.setText(String.valueOf(ss));
//        }else if(plus.getId() == R.id.plus4){
//            sss = sss+1;
//            sum2.setText(String.valueOf(sss));
//        }else if(plus.getId() == R.id.plus5){
//            sss = sss+2;
//            sum2.setText(String.valueOf(sss));
//        }else if(plus.getId() == R.id.plus6){
//            sss = sss+3;
//            sum2.setText(String.valueOf(sss));
//        }
//    }
//
//    public void Reset(View v) {
//        ss=0;
//        sss=0;
//        sum.setText(String.valueOf(ss));
//        sum2.setText(String.valueOf(sss));
//    }









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
        Log.i(TAG, "plus: dollarrate"+dollarrate);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: received");
                if(msg.what == 6){
                    String str = (String) msg.obj;
                    Log.i(TAG, "handleMessage: str="+str);
                }
                super.handleMessage(msg);
            }
        };

        //开启线程
        Thread t = new Thread(this);
        t.start();
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
            output.setText("请输入金额");
            Toast.makeText(MainActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
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
        startActivityForResult(config,1); //打开可返回窗口
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

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: Thread");

        //访问网络资源
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html = inputStream2String(in);
            Log.i(TAG, "run: html="+html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //回主线程
       Message msg = handler.obtainMessage();
       msg.what = 6;
       msg.obj = "hello";
       handler.sendMessage(msg);
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

