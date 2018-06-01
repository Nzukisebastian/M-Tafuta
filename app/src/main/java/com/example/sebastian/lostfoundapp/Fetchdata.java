package com.example.sebastian.lostfoundapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SEBASTIAN on 2/4/2018.
 */
public class Fetchdata extends AsyncTask<Void,Void,Void> {
    String dataparsed="";
    String singleparsed="";
    String json_url="";
    String data="";
    @Override
    protected Void doInBackground(Void... params) {
        json_url="http://ictchops.me.ke/fetchdata.php";
        try {
            URL url=new URL(json_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null){
                line=bufferedReader.readLine();
                data=data + line;
            }
            JSONArray ja=new JSONArray(data);
            for(int count=0;count<ja.length();count++){
                JSONObject jo=(JSONObject)ja.get(count);
                singleparsed="LOST ITEM:"+jo.get("report")+"\n";
                dataparsed=dataparsed + singleparsed+"\n";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Main4Activity.data.setText(this.dataparsed);
    }
}

