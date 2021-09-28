package com.example.myapplicationtestforlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends Activity {
    public static final String TAG="MainActivity";
    EditText dollarrate;
    EditText poundrate;
    EditText japanrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dollarrate = findViewById(R.id.dollarrate);
        poundrate = findViewById(R.id.poundrate);
        japanrate = findViewById(R.id.japanrate);
        Intent config =getIntent();
        float dollaror = config.getFloatExtra("dollarrate",0.0f);
        float poundor = config.getFloatExtra("poundrate",0.0f);
        float japanor = config.getFloatExtra("japanrate",0.0f);
        Log.i(TAG, "onCreate: dollaror="+dollaror);

        dollarrate.setText(String.valueOf(dollaror));
        poundrate.setText(String.valueOf(poundor));
        japanrate.setText(String.valueOf(japanor));
    }

    public void plus2(View plus2) {
        String inp1 = dollarrate.getText().toString();
        String inp2 = poundrate.getText().toString();
        String inp3 = japanrate.getText().toString();
        if(inp1.length()>0 && inp2.length()>0 &&inp3.length()>0){
            Float dollarrate = Float.parseFloat(inp1);
            Float poundrate = Float.parseFloat(inp2);
            Float japanrate = Float.parseFloat(inp3);

            Intent data = getIntent();
            data.putExtra("dollarrate",dollarrate);
            data.putExtra("poundrate",poundrate);
            data.putExtra("japanrate",japanrate);
            Log.i(TAG, "plus2: dollarrate="+dollarrate);
            setResult(3,data);
            finish();
        }else{
            Toast.makeText(MainActivity2.this, "请完整填写", Toast.LENGTH_SHORT).show();
        }
    }

}
