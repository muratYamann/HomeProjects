package com.dwderylmz.home.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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



    String ev_id;
    String ev_il;
    String ev_emlak_tip;
    String ev_alan;
    String ev_bina_yasi;
    String ev_oda_sayisi;
    String ev_bul_kat;
    String ev_fiyat;
    String ev_aciklama;
    String ev_resim_yolu;

    ArrayList<HomeItems> homeItems;
    ArrayList<String> itemTitles;
    ArrayList<String>itemImageUrl;
    Bundle bundle;
    TextView tvIl,tvEmlakTip,tvAlan,tvYasi,tvOdaSayisi,tvKat,tvFiyat,tvAciklama;
    String homePosition;
    int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        SetupVariable();
        bundle  = getIntent().getExtras();
        int data = bundle.getInt("position");
        homePosition = String.valueOf(data+1);


        try {
            String ws = new DetailActivity.WebService_homeGet(getApplicationContext()).execute(Const.ulrLocalHomeGet).get();

        } catch (Exception e) {
            System.err.print(e);
        }



    }


    private void SetupVariable(){

        // tvIl = (TextView)findViewById(R.id.tv) ;

        tvEmlakTip = (TextView)findViewById(R.id.tvEmlakTipi);
        tvAlan = (TextView)findViewById(R.id.tvEvAlani);
        tvYasi = (TextView)findViewById(R.id.tvBinaYasi);
        tvOdaSayisi = (TextView)findViewById(R.id.tvOdaSayisi);
        tvKat = (TextView)findViewById(R.id.tv_bulunduguKat);
        tvFiyat = (TextView)findViewById(R.id.tv_ev_fiyat);
        tvKat = (TextView)findViewById(R.id.tv_bulunduguKat);
        tvAciklama = (TextView)findViewById(R.id.tvAciklama);

    }

    class WebService_homeGet extends AsyncTask<String, String, String> {

        ArrayList<String> arrayListimage;
        ArrayList<HomeItems> sharedList;
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
            Log.d(TAG, "doInBackground: homePosition URL: " +brm_url);

            jsonParser = new JSONParser();
            arrayListimage = new ArrayList<>();
            sharedList = new ArrayList<HomeItems>();

            Log.d(TAG, "doInBackground: url" + brm_url);


            try {
                json = JSONParser.makeHttpRequest(brm_url, "GET");
                Log.d(TAG, "doInBackground: json:"+json);


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


                for (int i =0; i<s.size();i++){
                    Log.d(TAG, "doInBackground sEleman : "+s.get(i));
                }
       }catch (Exception e){}
            Log.d(TAG, "ws do: 5");

            return null;
        }

        @Override
        protected void onPostExecute(String results) {

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
