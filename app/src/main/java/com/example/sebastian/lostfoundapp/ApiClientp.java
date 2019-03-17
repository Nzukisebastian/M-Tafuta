package com.example.sebastian.lostfoundapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nzuki on 9/20/2018.
 */
public class ApiClientp {
    public static final String Base_url="http://ictchops.me.ke/gallaryapp/scripts/";
    public static Retrofit retrofit=null;
    public  static Retrofit getApiclientp(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
