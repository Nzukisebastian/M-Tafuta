package com.example.sebastian.lostfoundapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientf {

    public static final String Base_url="http://ictchops.me.ke/gallaryapp/scripts/";
    public static Retrofit retrofit=null;
    public  static Retrofit getApiclient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
