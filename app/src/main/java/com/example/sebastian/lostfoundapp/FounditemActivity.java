package com.example.sebastian.lostfoundapp;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
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

public class FounditemActivity extends AppCompatActivity {
    private RecyclerView recyclerViews;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private ApiInterfacef apiInterface;
    RecyclerAdapterf adapter;
    ProgressDialog progressDialog;
    private int page_number=1;
    private int item_count=5;
    //variables for pagination
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total=0;
    private  int view_threshold=5;
    public FounditemActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founditem2);
        progressBar= (ProgressBar)findViewById(R.id.progressbar);
        recyclerViews= (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerViews.setHasFixedSize(true);
        recyclerViews.setLayoutManager(layoutManager);
        apiInterface=ApiClientf.getApiclient().create(ApiInterfacef.class);

        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponsef>> call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponsef>>() {
            @Override
            public void onResponse(Call<List<DataResponsef>> call, Response<List<DataResponsef>> response) {

                List<Imagesf>images=response.body().get(1).getImages();
                adapter=new RecyclerAdapterf(images,getApplicationContext());
                recyclerViews.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"first page is loaded...",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<DataResponsef>> call, Throwable t) {

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
        Call<List<DataResponsef>>call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponsef>>() {
            @Override
            public void onResponse(Call<List<DataResponsef>> call, Response<List<DataResponsef>> response) {

                if(response.body().get(0).getStatus().equals("ok")){

                    List<Imagesf>images=response.body().get(1).getImages();
                    adapter.addImages(images);
                    Toast.makeText(getApplicationContext(),"Page"+page_number+"is loaded...",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No more images available...",Toast.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<DataResponsef>> call, Throwable t) {

            }
        });
    }
}
