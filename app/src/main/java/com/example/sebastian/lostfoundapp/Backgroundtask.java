package com.example.sebastian.lostfoundapp;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by SEBASTIAN on 11/13/2017.
 */
public class Backgroundtask extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    ProgressDialog loading;
    Backgroundtask(Context ctx){
        context = ctx;
    }
    @Override

    protected void onPreExecute() {
       // loading = ProgressDialog.show(context,"Downloading images...","Please wait...",true,true);
        progressDialog=ProgressDialog.show(context,"Verifying credentials...","Please wait...",true,true);
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://ictchops.me.ke/login.php";
        String register_url = "http://ictchops.me.ke/register.php";
        if(type.equals("login")){
            try{
                String email = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) result += line;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try{
                String email = params[1];
                String password = params[2];
                String phone=params[3];
                String fullname=params[4];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fullname,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        +"&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) result += line;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
            progressDialog.dismiss();
        if(result!=null && result.equals("login success")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        Intent i=new Intent(context,Gallary.class);
        context.startActivity(i);

    }else if(result!=null && result.equals("successfully registred")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            Intent i=new Intent(context,MainActivity.class);
            context.startActivity(i);
        }

        else if(result==null){
            Toast.makeText(context,"check your network connection",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        //we are
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
