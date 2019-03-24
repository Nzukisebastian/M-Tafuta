package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Fulldetails extends BaseActivity{

ImageView imgs;
   private TextView title,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulldetails);
        description=(TextView)findViewById(R.id.iddescription);
        imgs=(ImageView)findViewById(R.id.pic);
        //receiving data
        Intent intent=getIntent();
        String pic=intent.getExtras().getString("img");
        String details=intent.getExtras().getString("details");
//setting values to the variables
        Glide.with(this).load(pic).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imgs);
        //title.setText("The identification number is:"+ titles+"," + "kindly visit our nearest M-Tafuta branch for more information, Thank you!!");
        description.setText(details);
    }
}
