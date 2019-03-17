package com.example.sebastian.lostfoundapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nzuki on 9/20/2018.
 */
public interface ApiInterfacep {
    @GET("getpple.php")
    Call<List<DataResponsep>> getImages(@Query("page_number")int page,@Query("item_count")int items);
}
