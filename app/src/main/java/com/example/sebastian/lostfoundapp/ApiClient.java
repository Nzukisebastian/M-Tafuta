package com.example.sebastian.lostfoundapp;

/**
 * Created by SEBASTIAN on 7/4/2018.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SEBASTIAN on 6/29/2018.
 */
public class ApiClient {
    public static final String Base_url="http://ictchops.me.ke/gallaryapp/scripts/";
    public static Retrofit retrofit=null;
    public  static Retrofit getApiclient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}