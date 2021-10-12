package com.example.myapplicationtestforlayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainActivity4 extends AppCompatActivity {

    View view;
    TextView output;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.textView2);
        LocalDate localDate = LocalDate.now();
        output.setText(localDate + "plus" + LocalTime.now());
        Log.i("111", "on");

        Button btn = findViewById(R.id.button);

//        btn.setOnClickListener(this); //窗口对象this
//
//        //匿名类监听
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                output.setText("BYE");
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View view) {
//        String time = output.getText().toString();
//        output.setText("HI");
//    }

    }

    public void onClick(View view) {
        String time = output.getText().toString();
        output.setText("HI");
    }
}
