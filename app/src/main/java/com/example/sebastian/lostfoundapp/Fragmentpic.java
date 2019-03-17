package com.example.sebastian.lostfoundapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nzuki on 8/28/2018.
 */
public class Fragmentpic extends Fragment{
    private RecyclerView recyclerViews;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private ApiInterface apiInterface;
    RecyclerAdapter adapter;
    ProgressDialog progressDialog;
    private int page_number=1;
    private int item_count=10;
    //variables for pagination
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total=0;
    private  int view_threshold=10;
    View v;

    public Fragmentpic(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.callpic_fragment, container, false);
        progressBar= (ProgressBar)v.findViewById(R.id.progressbar);
        recyclerViews= (RecyclerView)v.findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerViews.setHasFixedSize(true);
        recyclerViews.setLayoutManager(layoutManager);
        apiInterface=ApiClient.getApiclient().create(ApiInterface.class);

        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponse>> call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(Call<List<DataResponse>> call, Response<List<DataResponse>> response) {

                List<Images>images=response.body().get(1).getImages();
                adapter=new RecyclerAdapter(images,getContext());
                recyclerViews.setAdapter(adapter);
                Toast.makeText(getContext(),"first page is loaded...",Toast.LENGTH_LONG).show();
                             progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {

            }
        });

        recyclerViews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount=layoutManager.getChildCount();
                totalItemCount=layoutManager.getItemCount();
                pastVisibleItems=layoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if(isLoading){

                        if(totalItemCount>previous_total){
                            isLoading=false;
                            previous_total=totalItemCount;
                        }
                    }
                    if(!isLoading &&(totalItemCount-visibleItemCount)<=(pastVisibleItems + view_threshold)){

                        page_number++;
                        performPageination();
                        isLoading=true;
                    }
                }

            }
        });
        return v;
    }

    private void performPageination(){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponse>>call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(Call<List<DataResponse>> call, Response<List<DataResponse>> response) {

                if(response.body().get(0).getStatus().equals("ok")){

                    List<Images>images=response.body().get(1).getImages();
                    adapter.addImages(images);
                    Toast.makeText(getContext(),"Page"+page_number+"is loaded...",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"No more images available...",Toast.LENGTH_LONG).show();
                }

               progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {

            }
        });
    }

    @Override
        public void onCreate(@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
       // lstcontact=new ArrayList<>();
        //lstcontact=add(new Contact("seba",R.drawable.ic_action_call));

        }
}