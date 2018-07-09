package com.example.sebastian.lostfoundapp;

import android.app.Application;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SEBASTIAN on 7/9/2018.
 */
public class MyApp extends Application {


    private Timer timer;
    private LogoutListener listener;

    public void startUserSession() {
        cancelTimer();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onSessionLogout();

            }
        },50000);
    }

    private void cancelTimer() {
        if(timer!=null)timer.cancel();
    }

    public void registerSessionListener(LogoutListener listener) {
        this.listener=listener;
    }

    public void onUserInteraction() {
        startUserSession();
    }
}
