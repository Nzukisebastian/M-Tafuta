package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {
    String data="";
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void getjson(View view){

        new BackgroundTask().execute();
    }
    class BackgroundTask extends AsyncTask<Void,Void,String> {
        String json_url="";
        String Json_string="";

        @Override
        protected void onPreExecute() {

            json_url="http://10.0.2.2/fetchdata.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while (Json_string!=null){
                    Json_string=bufferedReader.readLine();
                    data=data+Json_string;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            TextView textView=(TextView)findViewById(R.id.textview);
            textView.setText(result);
            json_string=result;

        }
    }
    public void parsejson(View view){
        if(json_string==null){
            Toast.makeText(getApplication(),"getjson first",Toast.LENGTH_LONG).show();

        }else{
            Intent intent=new Intent(this,Displaylistviews.class);
            intent.putExtra("data",data);
            startActivity(intent);
        }
    }
}
