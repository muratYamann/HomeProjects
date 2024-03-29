package com.dwderylmz.home.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwderylmz.home.R;
import com.dwderylmz.home.activity.DetailActivity;
import com.dwderylmz.home.model.HomeItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Murat on.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static String TAG="_recycler";
    ArrayList<HomeItems> homeItems;
    ArrayList<HomeItems> sharedList;



    Context context;
    public RecyclerViewAdapter(RecyclerView recyclerView, Context context, ArrayList<HomeItems> homeItems){
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
        final HomeItems current=homeItems.get(position);
        holder.ev_il.setText(current.getEv_il());
       // holder.homeAdress.setText(current.getHomeAdrees());
        holder.evFiyat.setText("Fiyat :"+current.getEv_fiyat()+" TL");
        holder.homeAdress.setText(current.getEv_emlak_tip());

        byte[] decodedString = Base64.decode(current.getEv_resim_yolu(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Log.d(TAG, "onBindViewHolder decodeByte: "+decodedByte);
        holder.homeImage.setImageBitmap(decodedByte);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(context,DetailActivity.class);
//                Bundle b = new Bundle();
//                b.putString("link",current.getEv_aciklama());
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ev_il,evFiyat,homeAdress;
        ImageView homeImage;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ev_il= (TextView) itemView.findViewById(R.id.home_name);
            homeAdress= (TextView) itemView.findViewById(R.id.home_adress);
            evFiyat= (TextView) itemView.findViewById(R.id.tv_ev_fiyat);
            homeImage= (ImageView) itemView.findViewById(R.id.home_img);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
