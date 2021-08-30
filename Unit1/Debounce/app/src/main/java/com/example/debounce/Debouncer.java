package com.example.debounce;

import android.os.Handler;

public class Debouncer {
    private int delay;
    private Handler handler;
    private Runnable internalRunnable;
    private boolean isRunning = false;
    public Debouncer(Runnable runnable, int delay) {
        this.delay = delay;
        this.handler = new Handler();
        this.internalRunnable = () -> {
            isRunning = false;
            runnable.run();
        };
    }

    public void execute() {
        if (isRunning) {
            // stop existing timer and start again
            handler.removeCallbacks(internalRunnable);
        } else {
            // simply start the timer
            isRunning = true;
        }
        handler.postDelayed(internalRunnable, delay);
    }
}
