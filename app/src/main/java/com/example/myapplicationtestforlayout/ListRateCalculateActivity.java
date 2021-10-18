package com.example.myapplicationtestforlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListRateCalculateActivity extends AppCompatActivity {

    EditText input;
    TextView output;
    Button button;
    String detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rate_calculate);

        TextView ratecalculatetitle = findViewById(R.id.ratecalculatetitle);
        input = findViewById(R.id.ratecalculatedetail);
        output = findViewById(R.id.rateresult);
        button  = findViewById(R.id.ratecalculate);

        Intent rate =getIntent();
        String title = rate.getStringExtra("title");
        detail = rate.getStringExtra("detail");

        ratecalculatetitle.setText(title);
    }

    public void rateplus(View plus) {
        String input1 = input.getText().toString();
        double input2 = Double.parseDouble(input1);
        double output1 = input2 * Double.parseDouble(detail);
        String output2 = String.format("%.2f",output1);

        output.setText(output2);
    }
}