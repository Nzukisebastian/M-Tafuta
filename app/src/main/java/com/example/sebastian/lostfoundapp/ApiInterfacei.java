package com.example.sebastian.lostfoundapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfacei {

    @GET("itemlost.php")
    Call<List<DataResponsei>> getImages(@Query("page_number")int page, @Query("item_count")int items);
}
