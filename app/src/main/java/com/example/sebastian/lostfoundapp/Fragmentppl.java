package com.example.sebastian.lostfoundapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nzuki on 8/28/2018.
 */
public class Fragmentppl extends Fragment {
    private RecyclerView recyclerViews;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private ApiInterfacep apiInterface;
    RecyclerAdapterp adapter;
    private int page_number=1;
    private int item_count=10;

    //variables for pagination
    private boolean isLoading=true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total=0;
    private  int view_threshold=10;
    View v;

    public Fragmentppl() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.pplfragment, container, false);
        progressBar= (ProgressBar)v.findViewById(R.id.progressbar);
        recyclerViews= (RecyclerView)v.findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerViews.setHasFixedSize(true);
        recyclerViews.setLayoutManager(layoutManager);
        apiInterface=ApiClientp.getApiclientp().create(ApiInterfacep.class);
        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponsep>> call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponsep>>() {
            @Override
            public void onResponse(Call<List<DataResponsep>> call, Response<List<DataResponsep>> response) {

                List<Imagesp>images=response.body().get(1).getImages();
                adapter=new RecyclerAdapterp(images,getContext());
                recyclerViews.setAdapter(adapter);
                Toast.makeText(getContext(),"first page is loaded...",Toast.LENGTH_LONG).show();
                ;               progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DataResponsep>> call, Throwable t) {

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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    private void performPageination(){

        progressBar.setVisibility(View.VISIBLE);
        Call<List<DataResponsep>>call=apiInterface.getImages(page_number,item_count);
        call.enqueue(new Callback<List<DataResponsep>>() {
            @Override
            public void onResponse(Call<List<DataResponsep>> call, Response<List<DataResponsep>> response) {

                if(response.body().get(0).getStatus().equals("ok")){

                    List<Imagesp>images=response.body().get(1).getImages();
                    adapter.addImages(images);
                    Toast.makeText(getContext(),"Page"+page_number+"is loaded...",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"No more images available...",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<DataResponsep>> call, Throwable t) {

            }
        });
    }
}
