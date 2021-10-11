package com.example.myapplicationtestforlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView sum2;
    TextView sum;
    int ss =0;
    int sss =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countmark);
        sum = findViewById(R.id.sum);
        sum2 = findViewById(R.id.sum2);
    }

    public void plus(View plus) {
        if(plus.getId() == R.id.plus1){
            ss = ss+1;
            sum.setText(String.valueOf(ss));
        }else if(plus.getId() == R.id.plus2){
            ss = ss+2;
            sum.setText(String.valueOf(ss));
        }else if(plus.getId() == R.id.plus3){
            ss = ss+3;
            sum.setText(String.valueOf(ss));
        }else if(plus.getId() == R.id.plus4){
            sss = sss+1;
            sum2.setText(String.valueOf(sss));
        }else if(plus.getId() == R.id.plus5){
            sss = sss+2;
            sum2.setText(String.valueOf(sss));
        }else if(plus.getId() == R.id.plus6){
            sss = sss+3;
            sum2.setText(String.valueOf(sss));
        }
    }

    public void Reset(View v) {
        ss=0;
        sss=0;
        sum.setText(String.valueOf(ss));
        sum2.setText(String.valueOf(sss));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score1",ss);
        outState.putInt("score2",sss);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ss = savedInstanceState.getInt("score1");
        sss = savedInstanceState.getInt("score2");
        show();
    }

    public void show(){
        sum.setText(ss);
        sum2.setText(sss);
    }
}