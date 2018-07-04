package com.example.sebastian.lostfoundapp;

/**
 * Created by SEBASTIAN on 7/4/2018.
 */
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SEBASTIAN on 6/29/2018.
 */
public interface ApiInterface {
    @GET("get_images.php")
    Call<List<DataResponse>> getImages(@Query("page_number")int page,@Query("item_count")int items);
}
