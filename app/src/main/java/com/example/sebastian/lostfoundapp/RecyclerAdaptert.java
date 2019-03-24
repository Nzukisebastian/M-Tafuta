package com.example.sebastian.lostfoundapp;
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
public class RecyclerAdaptert extends RecyclerView.Adapter<RecyclerAdaptert.MyViewHolder> {

    List<Imagest> imagesList;
    Context context;

    public RecyclerAdaptert(List<Imagest> images, Context context) {
        this.imagesList=images;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trace_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Imagest image=imagesList.get(position);
        String code=image.getImageid();
        String details=image.getDetails();
        holder.code.setText("code:"+code);
        holder.details.setText(details+"...");
        Glide.with(context).load(image.getImagepath()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.Album);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Fulldetails.class);
                intent.putExtra("details",image.getDetails());
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
        TextView details ;
        TextView code ;
        public MyViewHolder(View itemView) {
            super(itemView);
            Album= (ImageView) itemView.findViewById(R.id.album_image);
            details= (TextView) itemView.findViewById(R.id.album_title);
            code= (TextView) itemView.findViewById(R.id.code);
            cardView=(CardView) itemView.findViewById(R.id.cardview);


        }
    }
    public void addImages(List<Imagest>images){
        for(Imagest im : images){
            imagesList.add(im);
        }
        notifyDataSetChanged();
    }

}
