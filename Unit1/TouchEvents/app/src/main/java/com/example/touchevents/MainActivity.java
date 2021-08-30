package com.example.touchevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView xView = findViewById(R.id.x);
        TextView yView = findViewById(R.id.y);
        findViewById(R.id.container).setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("__TOUCH_EVENTS", "ACTION DOWN");
                xView.setText("X: PLACED " + motionEvent.getX());
                yView.setText("Y: PLACED " + motionEvent.getY());
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                Log.d("__TOUCH_EVENTS", "ACTION MOVE");
                xView.setText("X: MOVED " + motionEvent.getX());
                yView.setText("Y: MOVED " + motionEvent.getY());
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                Log.d("__TOUCH_EVENTS", "ACTION UP");
                xView.setText("X: LIFTED " + motionEvent.getX());
                yView.setText("Y: LIFTED " + motionEvent.getY());
            }

            return false;
        });
    }
}