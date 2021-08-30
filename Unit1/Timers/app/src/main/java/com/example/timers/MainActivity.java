package com.example.timers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private boolean isRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(() -> {
//                    TextView textView = findViewById(R.id.textView);
//                    textView.setText("TIMER FINISHED");
//                });
//            }
//        }, 5000);

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count ++;
                TextView textView = findViewById(R.id.textView);
                textView.setText("TIMER RUNS: " + count);
                handler.postDelayed(this, 1000);
            }
        };


        Button button = findViewById(R.id.button);
        button.setOnClickListener((view) -> {
            if (!isRunning) {
                isRunning = true;
                handler.postDelayed(runnable, 1000);
            } else {
                isRunning = false;
                handler.removeCallbacks(runnable);
            }
        });
        Log.d("__TIMER", "PROGRAM STARTED");

    }
}