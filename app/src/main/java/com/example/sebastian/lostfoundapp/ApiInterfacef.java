package com.example.sebastian.lostfoundapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfacef {

    @GET("founditem.php")
    Call<List<DataResponsef>> getImages(@Query("page_number")int page, @Query("item_count")int items);
}
