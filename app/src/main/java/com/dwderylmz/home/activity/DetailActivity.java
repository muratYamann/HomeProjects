package com.dwderylmz.home.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.dwderylmz.home.R;
import com.dwderylmz.home.model.HomeItems;
import com.dwderylmz.home.util.Const;
import com.dwderylmz.home.util.JSONParser;
import com.dwderylmz.home.util.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by macbookpro on 19.05.2017.
 */

public class DetailActivity extends Activity  {


    Bundle bundle;
    private TextView tvIl,tvEmlakTip,tvAlan,tvYasi,tvOdaSayisi,tvKat,tvFiyat,tvAciklama;
    String homePosition;
    int position;
    ImageView imgEv,imgEv2,imgEv3,imgEv4;
    private String resimYol = "";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        bundle  = getIntent().getExtras();
        int data = bundle.getInt("position");
        homePosition = String.valueOf(data+1);


        tvEmlakTip = (TextView)findViewById(R.id.tvEmlakTipiBilgi);
        tvAlan = (TextView)findViewById(R.id.tvEvAlaniBilgi);
        tvYasi = (TextView)findViewById(R.id.tvBinaYasiBilgi);
        tvOdaSayisi = (TextView)findViewById(R.id.tvOdaSayisiBilgi);
        tvKat = (TextView)findViewById(R.id.tv_bulunduguKatBilgi);
        tvFiyat = (TextView)findViewById(R.id.tv_ev_fiyat);
        tvKat = (TextView)findViewById(R.id.tv_bulunduguKatBilgi);
        tvAciklama = (TextView)findViewById(R.id.tvEvAciklamaBilgi);
        imgEv = (ImageView)findViewById(R.id.home_img);
        imgEv2 = (ImageView)findViewById(R.id.img2);
        imgEv3 = (ImageView)findViewById(R.id.img3);
        imgEv4 = (ImageView)findViewById(R.id.img4);

        try {
            String ws = new DetailActivity.WebService_homeGet(getApplicationContext()).execute(Const.ulrLocalHomeGet).get();

        } catch (Exception e) {
            System.err.print(e);
        }



    }


    class WebService_homeGet extends AsyncTask<String, String, String> {

        ArrayList<String> arrayListimage;
        ArrayList<HomeItems> sharedList;
        ArrayList<String> resimler;
        Context ctx;
        private String TAG = "background";


        // JSON parser Class
        JSONParser jsonParser;
        JSONObject jsonObject;
        String json;
        ProgressDialog pd = null;

        public WebService_homeGet(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            String brm_url = params[0];
            brm_url+=homePosition;
            Log.d(TAG, "detail: homePosition URL: " +brm_url);

            jsonParser = new JSONParser();
            arrayListimage = new ArrayList<>();
            sharedList = new ArrayList<HomeItems>();

            Log.d(TAG, "detail: url" + brm_url);


            try {
                json = JSONParser.makeHttpRequest(brm_url, "GET");
                Log.d(TAG, "detail: json:"+json);


/*
                ArrayList<String>s = new ArrayList<>();
                JSONArray jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    s.add(jsonobject.getString("ev_il"));

                    String id = jsonobject.getString("ev_id");
                    String il = jsonobject.getString("ev_il");
                    String tip = jsonobject.getString("ev_emlak_tip");
                    String alan = jsonobject.getString("ev_alan");
                    String ev_oda_sayisi = jsonobject.getString("ev_oda_sayisi");
                    String ev_bina_yasi = jsonobject.getString("ev_bina_yasi");
                    String ev_bul_kat = jsonobject.getString("ev_bul_kat");
                    String ev_fiyat = jsonobject.getString("ev_fiyat");
                    String ev_aciklama = jsonobject.getString("ev_aciklama");
                    String ev_resimler = jsonobject.getString("resimler");



                    Log.d(TAG, "doInBackground: ev" + "_id :"+id);
                    Log.d(TAG, "doInBackground: il :"+il);
                    Log.d(TAG, "doInBackground: tip : "+tip);
                    Log.d(TAG, "doInBackground: alan :"+alan);
                    Log.d(TAG, "doInBackground: "+ev_resimler);
                }
*/


       }catch (Exception e){}
            Log.d(TAG, "ws do: 5");

            return json;
        }

        @Override
        protected void onPostExecute(String results) {

            try {
                Log.d(TAG, "detail: json:"+json);

                resimler = new ArrayList<>();

                JSONArray jsonarray = new JSONArray(json);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);


                    String id = jsonobject.getString("ev_id");
                    String il = jsonobject.getString("ev_il");
                    String tip = jsonobject.getString("ev_emlak_tip");
                    String alan = jsonobject.getString("ev_alan");
                    String ev_oda_sayisi = jsonobject.getString("ev_oda_sayisi");
                    String ev_bina_yasi = jsonobject.getString("ev_bina_yasi");
                    String ev_bul_kat = jsonobject.getString("ev_bul_kat");
                    String ev_fiyat = jsonobject.getString("ev_fiyat");
                    String ev_aciklama = jsonobject.getString("ev_aciklama");
                    String ev_resimler = jsonobject.getString("resimler");


                    try {

                        Log.d(TAG, "onPostExecute: resimler"+ev_resimler);
                        JSONArray jsonarrayResimler = new JSONArray(ev_resimler);
                        for (int j = 0; j < jsonarrayResimler.length(); j++) {
                            JSONObject jsonobjectt = jsonarrayResimler.getJSONObject(i);
                            resimYol = jsonobjectt.getString("resim_yol");
                            resimler.add(resimYol);
                            Log.d(TAG, "onPostExecute: resim_yol  :"+resimYol);
                        }

                        Log.d(TAG, "doInBackground: resim yolu:"+resimYol);
                    }catch(Exception e) {e.printStackTrace();}





                    Log.d(TAG, "detail: ev:"+id);
                    Log.d(TAG, "detail: il :"+il);
                    Log.d(TAG, "detail: tip : "+tip);
                    Log.d(TAG, "detail: alan :"+alan);
                    Log.d(TAG, "detail: "+ev_resimler);



                    tvEmlakTip.setText(tip);
                    tvAlan.setText(alan);
                    tvOdaSayisi.setText(ev_oda_sayisi);
                    tvYasi.setText(ev_bina_yasi);
                    tvKat.setText(ev_bul_kat);
                    tvFiyat.setText(ev_fiyat);
                    tvAciklama.setText(ev_aciklama);

                    int resimSayisi = resimler.size();
                    if (resimSayisi == 4){

                        byte[] decodedString = Base64.decode(resimler.get(0), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imgEv.setImageBitmap(decodedByte);


                        byte[] decodedStringg = Base64.decode(resimler.get(1), Base64.DEFAULT);
                        Bitmap decodedBytee = BitmapFactory.decodeByteArray(decodedStringg, 0, decodedStringg.length);
                        imgEv2.setImageBitmap(decodedBytee);


                        byte[] decodedStringgg = Base64.decode(resimler.get(2), Base64.DEFAULT);
                        Bitmap decodedByteee = BitmapFactory.decodeByteArray(decodedStringgg, 0, decodedStringgg.length);
                        imgEv3.setImageBitmap(decodedByteee);


                        byte[] decodedStringggg = Base64.decode(resimler.get(3), Base64.DEFAULT);
                        Bitmap decodedByteeee = BitmapFactory.decodeByteArray(decodedStringggg, 0, decodedStringggg.length);
                        imgEv4.setImageBitmap(decodedByteeee);


                    }
                    if (resimSayisi == 3){

                        byte[] decodedString = Base64.decode(resimler.get(0), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imgEv.setImageBitmap(decodedByte);


                        byte[] decodedStringg = Base64.decode(resimler.get(1), Base64.DEFAULT);
                        Bitmap decodedBytee = BitmapFactory.decodeByteArray(decodedStringg, 0, decodedStringg.length);
                        imgEv2.setImageBitmap(decodedBytee);


                        byte[] decodedStringgg = Base64.decode(resimler.get(2), Base64.DEFAULT);
                        Bitmap decodedByteee = BitmapFactory.decodeByteArray(decodedStringgg, 0, decodedStringgg.length);
                        imgEv3.setImageBitmap(decodedByteee);


                    }

                    if (resimSayisi == 2){

                        byte[] decodedString = Base64.decode(resimler.get(0), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imgEv.setImageBitmap(decodedByte);


                        byte[] decodedStringg = Base64.decode(resimler.get(1), Base64.DEFAULT);
                        Bitmap decodedBytee = BitmapFactory.decodeByteArray(decodedStringg, 0, decodedStringg.length);
                        imgEv2.setImageBitmap(decodedBytee);

                    }


                    byte[] decodedString = Base64.decode(resimler.get(0), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    Log.d(TAG, "onBindViewHolder decodeByte: "+decodedByte);
                    imgEv.setImageBitmap(decodedByte);


                    // tvIl,tvEmlakTip,tvAlan,tvYasi,tvOdaSayisi,tvKat,tvFiyat,tvAciklama;


                }

            }catch (Exception e){}





            Log.d(TAG, "onPostExecute: result" + results);
            Log.d(TAG, "onPostExecute: sharedListSize"+sharedList.size());
        }
    }


    @Override
    protected void onPause() {
        super.onPause();}
    @Override
    protected void onDestroy() {
        super.onDestroy();}
    @Override
    protected void onRestart() {
        super.onRestart();}



/**

    class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FragmentSlider f1 = new FragmentSlider();
            Bundle b = new Bundle();

            if (0 == position) {
                // FragmentSlider f1 = new FragmentSlider();
                b.putInt("position",position);
                f1.setArguments(b);
                return f1;
            }else {
                b.putInt("position", position);
                f1.setArguments(b);
                return f1;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

        */


}
