package com.example.sebastian.lostfoundapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Adminlogin extends AppCompatActivity implements View.OnClickListener  {
    EditText etemails,etpasswords;
    Button login,regi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        etemails = (EditText) findViewById(R.id.etemail);
        etpasswords = (EditText) findViewById(R.id.etpassword);
        login=(Button)findViewById(R.id.btn_logi);
        regi=(Button)findViewById(R.id.btn_regi);
        login.setOnClickListener(this);
        regi.setOnClickListener(this);
    }
    private void login() {
        String email=etemails.getText().toString();
        String password=etpasswords.getText().toString();
        String type="login";
        Admin admin=new Admin(this);
        admin.execute(type,email,password);
    }
    @Override
    public void onClick(View v) {
        if (v == login) {
            login();
        }
    }
}
