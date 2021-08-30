package com.example.counter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int count = 0;
    private int value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button increment = findViewById(R.id.increment);
        Button decrement = findViewById(R.id.decrement);
        TextView counter = findViewById(R.id.counter);

        increment.setOnClickListener((view) -> {
            count ++;
            counter.setText(count + "");
        });

        System.out.println(value);

        decrement.setOnClickListener((view) -> {
            count --;
            counter.setText(count + "");
        });
    }
}