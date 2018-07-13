package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity  extends AppCompatActivity implements View.OnClickListener {
    EditText etemails,etpasswords;
    Button login,regi;
    TextView forgets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etemails = (EditText) findViewById(R.id.etemail);
        etpasswords = (EditText) findViewById(R.id.etpassword);
        login=(Button)findViewById(R.id.btn_logi);
        regi=(Button)findViewById(R.id.btn_regi);
        forgets = (TextView) findViewById(R.id.forget);
        login.setOnClickListener(this);
        regi.setOnClickListener(this);
        forgets.setOnClickListener(this);
    }
    private void login() {
        String email=etemails.getText().toString();
        String password=etpasswords.getText().toString();
        String type="login";
        Backgroundtask backgroundtask=new Backgroundtask(this);
        backgroundtask.execute(type,email,password);
    }
    private void register() {
        startActivity(new Intent(this,Registers.class));

    }
    private void forget(){
        Intent i=new Intent(MainActivity.this,Passwordreset.class);
        startActivity(i);
    }
    @Override
    public void onClick(View v) {
      if(v==login){
       login();
      }
      else if(v==regi){
         register();
      }
      else if(v==forgets){
          forget();
      }
    }
}
