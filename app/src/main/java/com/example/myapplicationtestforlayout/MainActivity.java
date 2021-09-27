package com.example.myapplicationtestforlayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

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
    }

    public void plus(View plus) {
        String inp = input.getText().toString();
        if(inp.length()>0) {
            double x = Double.parseDouble(inp);
            if (plus.getId() == R.id.dollar) {
                x = x * 7.6f;
                output.setText(String.valueOf(x));
            } else if (plus.getId() == R.id.pound) {
                x = x * 8.7f;
                output.setText(String.valueOf(x));
            } else if (plus.getId() == R.id.japan) {
                x = x * 22;
                output.setText(String.valueOf(x));
            }
        }else{
            output.setText("请输入金额");
            Toast.makeText(MainActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
    }

}

