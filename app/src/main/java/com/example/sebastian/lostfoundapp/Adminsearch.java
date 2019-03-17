package com.example.sebastian.lostfoundapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Adminsearch extends AppCompatActivity implements View.OnClickListener {
    EditText search;
    ImageView ivadd,ivlook,ivdelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsearch);
        search = (EditText) findViewById(R.id.etsearch);
        ivadd=(ImageView)findViewById(R.id.etadd);
        ivlook=(ImageView)findViewById(R.id.etlook);
        ivdelete=(ImageView)findViewById(R.id.etdelete);
        ivadd.setOnClickListener(this);
        ivlook.setOnClickListener(this);
        ivdelete.setOnClickListener(this);
    }
    private void add(){
        String look=search.getText().toString();
        String type="add";
        Adminbackend adminbackend=new Adminbackend(this);
        adminbackend.execute(type,look);
    }
    private void find() {
        String look=search.getText().toString();
        String type="find";
        Adminbackend adminbackend=new Adminbackend(this);
        adminbackend.execute(type,look);
    }
    private void delete(){
        String look=search.getText().toString();
        String type="delete";
        Adminbackend adminbackend=new Adminbackend(this);
        adminbackend.execute(type,look);
    }
    @Override
    public void onClick(View v) {
        if(v==ivadd){
            add();
        }
        else if(v==ivlook){
            find();
        }
        else if(v==ivdelete){
            delete();
        }
    }
}
