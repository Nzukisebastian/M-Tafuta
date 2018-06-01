package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etemails,etpasswords;
    TextView forgets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etemails = (EditText) findViewById(R.id.etemail);
        etpasswords = (EditText) findViewById(R.id.etpassword);
        forgets = (TextView) findViewById(R.id.forget);
        forgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Passwordreset.class);
                startActivity(i);
            }
        });
    }
    public void login(View view){
        String email=etemails.getText().toString().trim().toLowerCase();
        String password=etpasswords.getText().toString().trim().toLowerCase();
        String type="login";
        if(password!=null && password.length()>3){
            Backgroundtask backgroundworker=new Backgroundtask(this);
            backgroundworker.execute(type,email,password);

        }else{
            Toast.makeText(MainActivity.this,"invalid password",Toast.LENGTH_LONG).show();

        }
    }
    public void regi(View view){
        startActivity(new Intent(this,Registers.class));
    }
}
