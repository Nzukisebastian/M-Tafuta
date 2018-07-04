package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gallary extends AppCompatActivity {

    private RecyclerView recyclerViews;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private ApiInterface apiInterface;
    RecyclerAdapter adapter;
    private int page_number=1;
    private int item_count=10;

    //variables for pagination
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total=0;
    private  int view_threshold=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);
        progressBar= (ProgressBar) findViewById(R.id.progressbar);
        recyclerViews= (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(this,2);
        recyclerViews.setHasFixedSize(true);
        recyclerViews.setLayoutManager(layoutManager);
        apiInterface=ApiClient.getApiclient().create(ApiInterface.class);
        progressBar.setVisibility(View.VISIBLE);

        Call<List<DataResponse>> call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(Call<List<DataResponse>> call, Response<List<DataResponse>> response) {

                List<Images>images=response.body().get(1).getImages();
                adapter=new RecyclerAdapter(images,Gallary.this );
                recyclerViews.setAdapter(adapter);
                Toast.makeText(Gallary.this,"first page is loaded...",Toast.LENGTH_LONG).show();
                ;               progressBar.setVisibility(View.GONE);
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
                    Toast.makeText(Gallary.this,"Page"+page_number+"is loaded...",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Gallary.this,"No more images available...",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<DataResponse>> call, Throwable t) {

            }
        });
    }
    public void image(View view){

        startActivity(new Intent(this, Gallary.class));
    }
    public void info(View view){

        startActivity(new Intent(this, Main4Activity.class));
    }

}
