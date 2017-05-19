package com.dwderylmz.home.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwderylmz.home.R;
import com.dwderylmz.home.activity.DetailActivity;
import com.dwderylmz.home.model.HomeItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Murat on.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<HomeItem> homeItems;
    Context context;
    public RecyclerViewAdapter(Context context, ArrayList<HomeItem> homeItems){
        this.homeItems=homeItems;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custum_row_news_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        final HomeItem current=homeItems.get(position);
        holder.homeName.setText(current.getHomeName());
        holder.homeAdress.setText(current.getHomeAdrees());
        holder.evFiyat.setText(current.getHomeID());
        Picasso.with(context).load(current.getHomeImage()).into(holder.homeImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,DetailActivity.class);
                Bundle b = new Bundle();
                b.putString("link",current.getHomeAdrees());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView homeName,evFiyat,homeAdress;
        ImageView homeImage;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            homeName= (TextView) itemView.findViewById(R.id.home_name);
            homeAdress= (TextView) itemView.findViewById(R.id.home_adress);
            evFiyat= (TextView) itemView.findViewById(R.id.tv_ev_fiyat);
            homeImage= (ImageView) itemView.findViewById(R.id.home_img);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
