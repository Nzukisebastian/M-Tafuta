package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener  {

    ImageView ivadd,ivlook,ivdelete;
    RecyclerView mRecyclerview;
    private EditText editTextId;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    RequestQueue mRequest;
    List<ModelList> mListitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editTextId = (EditText) findViewById(R.id.editTextId);
        ivadd=(ImageView)findViewById(R.id.etadd);
        ivdelete=(ImageView)findViewById(R.id.etdelete);
        ivdelete.setOnClickListener(this);
        ivadd.setOnClickListener(this);
        mRecyclerview=(RecyclerView)findViewById(R.id.recyclerTemp);
        mRecyclerview=(RecyclerView)findViewById(R.id.recyclerTemp);
        mRequest= Volley.newRequestQueue(getApplicationContext());
        mListitems=new ArrayList<>();
        //request();
        mManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter=new AdapterList(mListitems,Main2Activity.this);
        mRecyclerview.setAdapter(mAdapter);
    }
    private void request(){
       // int id =6;
        String id = editTextId.getText().toString().trim();
        final String url="http://ictchops.me.ke/imageupload/getallimages.php?ids="+id;
        JsonArrayRequest requestimage=new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("JSONResponse",response.toString());
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject data=response.getJSONObject(i);
                        //creating an object of class Modellist and then adding values to the object by calling methods
                        //getting information from the server ie id,title,kitengerani,image then passing the parameters to modellist.java
                        //using set() and get() methods to add and update data freom the server
                        ModelList model=new ModelList();
                        model.setId(data.getString("id"));
                        model.setTitle(data.getString("title"));
                        model.setKaterangan(data.getString("kitengerani"));
                        model.setImg(data.getString("image"));
                        //adding the object to List for storage
                        mListitems.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERRORRequest","Error:"+error.getMessage());

                    }
                });
        mRequest.add(requestimage);
    }
    public void image(View view){

        request();
        //startActivity(new Intent(this, Main2Activity.class));
    }
    public void info(View view){

        startActivity(new Intent(this, Main4Activity.class));
    }


    private void add(){
        String code=editTextId.getText().toString();
        String type="add";
        Adminbackend adminbackend=new Adminbackend(this);
        adminbackend.execute(type,code);
    }

    private void delete(){
        String code=editTextId.getText().toString();
        String type="delete";
        Adminbackend adminbackend=new Adminbackend(this);
        adminbackend.execute(type,code);
    }
    @Override
    public void onClick(View v) {
        if(v==ivadd){
            add();
        }
        else if(v==ivdelete){
            delete();
        }
    }
}
