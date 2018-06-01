package com.example.sebastian.lostfoundapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Reportitem extends AppCompatActivity {
EditText report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportitem);
        report=(EditText)findViewById(R.id.report);
    }
    public void report(View view){
      String reports=report.getText().toString();
        String type="send";
        Reportbackground backgroundworker=new Reportbackground(this);
        backgroundworker.execute(type,reports);
    }
}
