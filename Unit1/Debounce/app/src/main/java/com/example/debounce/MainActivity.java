package com.example.debounce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Debounce delay until an action is no longer being repeated
    // Throttle only execute every so often
    // Delay only execute after a delay has occured.

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

//        Debouncer debouncer = new Debouncer(() -> {
//            textView.setText("I WAS DEBOUNCED");
//        }, 1000);

        Throttler throttler = new Throttler(() -> {
            count ++;
            textView.setText("Count: " + count);
        }, 2000);

        button.setOnClickListener((view) -> {
//          debouncer.execute();
            throttler.execute();
        });


    }
}