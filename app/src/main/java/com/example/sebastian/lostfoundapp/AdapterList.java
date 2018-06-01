package com.example.sebastian.lostfoundapp;

/**
 * Created by SEBASTIAN on 2/1/2018.
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

public class AdapterList extends RecyclerView.Adapter<AdapterList.HolderItem>{
    List<ModelList> mListItem;
    Context context;
    public AdapterList(List<ModelList> mListItem, Context context) {
        this.mListItem = mListItem;
        this.context = context;}

    @Override
    public HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rows,parent,false);
        HolderItem holder=new HolderItem(layout);
        return holder;
    }
    @Override
    public void onBindViewHolder(final HolderItem holder, final int position) {
         final ModelList mlist=mListItem.get(position);
        holder.tv_title.setText(mlist.getTitle());
        holder.tv_katerangan.setText(mlist.getKaterangan());
        /*loading image*/
       Glide.with(context).load(mlist.getImg()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.thubnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Fulldetails.class);
                intent.putExtra("img",mlist.getImg());
                intent.putExtra("title",mlist.getTitle());
                intent.putExtra("description",mlist.getKaterangan());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class HolderItem extends RecyclerView.ViewHolder{
       ImageView thubnail;
        TextView tv_title,tv_katerangan;
        CardView cardView;
        public ImageView imgs;

        public HolderItem(View v) {
            super(v);
            thubnail=(ImageView)v.findViewById(R.id.img_cover);
            tv_title=(TextView) v.findViewById(R.id.tv_title);
            tv_katerangan=(TextView) v.findViewById(R.id.tv_description);
            cardView=(CardView)v.findViewById(R.id.cardview);
        }
    }
}

