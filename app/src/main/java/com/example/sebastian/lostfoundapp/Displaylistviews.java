package com.example.sebastian.lostfoundapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Displaylistviews extends AppCompatActivity {
    String json_string="";
    String data="";
    ContactsAdapter contactsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaylistviews);
        listView=(ListView)findViewById(R.id.listview);
        contactsAdapter=new ContactsAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactsAdapter);
        data=getIntent().getExtras().getString("data");
        try{
            JSONArray jsonArray=new JSONArray(data);
            String name,email,mobile;
            int count=0;
            while(count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);
                name=jo.getString("name");
                email=jo.getString("password");
                mobile=jo.getString("username");
                Contacts contacts=new Contacts(name,email,mobile);
                contactsAdapter.add(contacts);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
