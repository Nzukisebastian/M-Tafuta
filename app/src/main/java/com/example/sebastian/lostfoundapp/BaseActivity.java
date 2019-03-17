package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kosalgeek.android.photoutil.MainActivity;

/**
 * Created by SEBASTIAN on 7/9/2018.
 */
public class BaseActivity extends AppCompatActivity implements LogoutListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp)getApplication()).registerSessionListener(this);

        ((MyApp)getApplication()).startUserSession();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        ((MyApp)getApplication()).onUserInteraction();
    }

    @Override
    public void onSessionLogout() {
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }
}
