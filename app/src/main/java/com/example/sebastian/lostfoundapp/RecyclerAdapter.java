package com.example.sebastian.lostfoundapp;

/**
 * Created by SEBASTIAN on 7/4/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by SEBASTIAN on 6/29/2018.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Images> imagesList;
    private Context context;
    public RecyclerAdapter(List<Images>imagesList,Context context){
        this.imagesList=imagesList;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Images image=imagesList.get(position);
        holder.AlbumTitle.setText("image"+ image.getImageid());
        Glide.with(context).load(image.getImagepath()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.Album);
   holder.cardView.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent=new Intent(context,Fulldetails.class);
           intent.putExtra("title",image.getImageid());
           intent.putExtra("img",image.getImagepath());
           context.startActivity(intent);
       }
   });

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView Album;
        TextView AlbumTitle ;
        public MyViewHolder(View itemView) {
            super(itemView);
            Album= (ImageView) itemView.findViewById(R.id.album_image);
            AlbumTitle= (TextView) itemView.findViewById(R.id.album_title);
            cardView=(CardView) itemView.findViewById(R.id.cardview);


        }
    }
    public void addImages(List<Images>images){
        for(Images im : images){
            imagesList.add(im);
        }
        notifyDataSetChanged();
    }

}
