package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Link extends AppCompatActivity implements View.OnClickListener {
    Button login,regi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        login=(Button)findViewById(R.id.btn_logi);
        regi=(Button)findViewById(R.id.btn_admin);
        login.setOnClickListener(this);
        regi.setOnClickListener(this);
    }
    private void login() {
        startActivity(new Intent(this,MainActivity.class));
    }
    private void register() {
        startActivity(new Intent(this,Adminreg.class));

    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            login();
        } else if (v == regi) {
            register();
        }
    }
}
