package com.example.debounce;

import android.os.Handler;

public class Throttler {
    private Runnable runnable;
    private Handler handler;
    private int delay;
    private boolean isRunning = false;
    public Throttler(Runnable runnable, int delay) {
        this.runnable = runnable;
        this.delay = delay;
        this.handler = new Handler();
    }

    public void execute() {
        if(!isRunning) {
            isRunning = true;
            runnable.run();
            handler.postDelayed(() -> {
                isRunning = false;
            }, delay);
        }
    }
}
