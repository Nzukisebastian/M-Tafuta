package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

public class FragmentAdminMissingPerson extends Fragment implements View.OnClickListener{

    ImageView ivadd,ivfind,ivdelete;
    RecyclerView mRecyclerview;
    private EditText editTextId;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    RequestQueue mRequest;
    List<ModelList> mListitems;
    ProgressBar progressBar;
    View v;
    public FragmentAdminMissingPerson() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmentadmincriminal, container, false);
        editTextId = (EditText)v. findViewById(R.id.editTextId);
        ivadd=(ImageView)v.findViewById(R.id.ivadd);
        ivdelete=(ImageView)v.findViewById(R.id.ivdelete);
        ivfind=(ImageView)v.findViewById(R.id.ivsearch);
        ivdelete.setOnClickListener(this);
        ivadd.setOnClickListener(this);
        ivfind.setOnClickListener(this);
        mRecyclerview=(RecyclerView)v.findViewById(R.id.recyclerTemp);
        progressBar=(ProgressBar) v.findViewById(R.id.pb);
        mRequest= Volley.newRequestQueue(getActivity());
        mListitems=new ArrayList<>();
        //request();
        mManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter=new AdapterList(mListitems,getActivity());
        mRecyclerview.setAdapter(mAdapter);
        return v;
    }

    private void request(){
        progressBar.setVisibility(View.VISIBLE);
        // int id =6;
        String id = editTextId.getText().toString().trim();
        final String url="http://ictchops.me.ke/gallaryapp/scripts/missperson.php?ids="+id;
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
                        model.setTitle(data.getString("token"));
                        model.setKaterangan(data.getString("details"));
                        model.setImg(data.getString("image"));
                        //adding the object to List for storage
                        mListitems.add(model);
                        progressBar.setVisibility(View.GONE);
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


    public void info(View view){

        startActivity(new Intent(getActivity(), Main4Activity.class));
    }

    private void add(){
        String code=editTextId.getText().toString();
        String type="add";
        Adminbackend adminbackend=new Adminbackend(getActivity());
        adminbackend.execute(type,code);
    }

    private void delete(){
        String code=editTextId.getText().toString();
        String type="delete";
        Adminbackend adminbackend=new Adminbackend(getActivity());
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
        else if(v==ivfind){
            request();
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
}
