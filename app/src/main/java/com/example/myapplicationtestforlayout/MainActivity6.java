package com.example.myapplicationtestforlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {


    EditText input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);
        input = findViewById(R.id.textView4);

        Button btn = findViewById(R.id.button2);

        //匿名类监听
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tem1 = Double.parseDouble(input.getText().toString());
                double tem2 = (tem1 * 9 / 5) + 32;
                output = findViewById(R.id.textView5);
                output.setText(Double.toString(tem2));
            }
        });

    }
}
