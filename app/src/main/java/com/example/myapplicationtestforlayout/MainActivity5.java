package com.example.myapplicationtestforlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {

    RadioButton male;
    RadioButton female;
    TextView Result;
    EditText text1;
    EditText text2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        setViews();
    }
    public void setViews() {
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);//判断男女
        Result = (TextView) findViewById(R.id.Result);
        text1 = (EditText)findViewById(R.id.height);
        text2 = (EditText)findViewById(R.id.weight);

        btn = (Button)findViewById(R.id.btn);
        //btn.setOnClickListener(this);

    }

    public void onClick(View v) {
        double x = Double.parseDouble(text1.getText().toString());//身高
        double y = Double.parseDouble(text2.getText().toString());//体重//体重除以身高的平方
        double res;
        String str = "Your BMI is";
        if (x <= 0 || y <= 0) {
            Result.setText("值异常，不计算");
            return;
        }
        x = x / 100;
        res = y / (x * x);
        String str1 = String.format("%.2f", res);
        str = str + str1;
        if (female.isChecked()) {
            str += "体型:";
            if (res < 19)
                str += "过轻";
            else if (res < 24)
                str += "适中";
            else if (res < 30)
                str += "超重";
            else
                str += "肥胖";
            Result.setText(str);
        } else {
            str += "体型:";
            if (res < 18)
                str += "过轻";
            else if (res < 24)
                str += "适中";
            else if (res < 28)
                str += "超重";
            else
                str += "肥胖";
            Result.setText(str);
        }
    }
}