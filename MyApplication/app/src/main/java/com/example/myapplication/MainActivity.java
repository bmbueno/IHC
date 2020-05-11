package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView numberOne;
    TextView numberTwo;

    TextView novo;

    Button sum;

    TextView result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOne = findViewById(R.id.numberOne);
        numberTwo = findViewById(R.id.numberTwo);

        result1 = findViewById(R.id.result1);

        sum = findViewById(R.id.button);
        sum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int one = Integer.parseInt(numberOne.getText().toString());
                int two = Integer.parseInt(numberTwo.getText().toString());
                int result = one + two;
                result1.setText("result: " + result);
            }
        });
    }

}
