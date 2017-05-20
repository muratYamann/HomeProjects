package com.dwderylmz.home.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.dwderylmz.home.R;
import com.dwderylmz.home.model.HomeItems;
import com.dwderylmz.home.util.Const;
import com.dwderylmz.home.util.DeviceUtils;
import com.dwderylmz.home.util.JSONParser;
import com.dwderylmz.home.util.PreCachingLayoutManager;
import com.dwderylmz.home.util.RecyclerItemClickListener;
import com.dwderylmz.home.util.RecyclerViewAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private static String TAG = "_main";
    private RecyclerView recyclerView;
    private CardView cardview;
    private  String resimYol ="";
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   mHandler = new Handler();
     //   mHandler.postDelayed(m_Runnable,5000);


        Log.d(TAG, "onCreate: main____");


        try {
            String ws = new WebService_getPosts(getApplicationContext()).execute(Const.ulrLocal).get();
            Log.d(TAG, "onCreate: next ws"+ws);

        } catch (Exception e) {
            System.err.print(e);
        }

        cardview = (CardView)findViewById(R.id.cardview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Toast.makeText(MainActivity.this, "Clicked"+position, Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("position",position);
                        i.putExtras(b);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        //Setup layout manager- for optimization created pre cachehing layout
        PreCachingLayoutManager layoutManager = new PreCachingLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getApplicationContext()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            Toast.makeText(MainActivity.this,"in runnable",Toast.LENGTH_SHORT).show();
            try {
                Log.d(TAG, "run: repeat");
                String ws = new WebService_getPosts(getApplicationContext()).execute(Const.ulrLocal).get();
            }catch (Exception e){}
            MainActivity.this.mHandler.postDelayed(m_Runnable, 30000);
        }
    };//runnable


    class WebService_getPosts extends AsyncTask<String, String, String> {

        ArrayList<String> arrayListimage;
        ArrayList<HomeItems> sharedList;
        Context ctx;
        private String TAG = "background";


        // JSON parser Class
        JSONParser jsonParser;
        JSONObject jsonObject;
        String json;
        ProgressDialog pd = null;

        public WebService_getPosts(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground: Entry");
            String brm_url = params[0];
            jsonParser = new JSONParser();
            arrayListimage = new ArrayList<>();
            sharedList = new ArrayList<HomeItems>();

            Log.d(TAG, "doInBackground: url" + brm_url);


             try {
                json = JSONParser.makeHttpRequest(brm_url, "GET");
                Log.d(TAG, "doInBackground: json:"+json);



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
                                Log.d(TAG, "onPostExecute: resim_yol  :"+resimYol);
                            }
                            Log.d(TAG, "doInBackground: resim yolu:"+resimYol);
                        }catch(Exception e) {e.printStackTrace();}


                        sharedList.add(new HomeItems(id,il,tip,alan,ev_bina_yasi,ev_oda_sayisi,ev_bul_kat,ev_fiyat,ev_aciklama,resimYol));

                        Log.d(TAG, "doInBackground: ev" + "_id :"+id);
                        Log.d(TAG, "doInBackground: il :"+il);
                        Log.d(TAG, "doInBackground: tip : "+tip);
                        Log.d(TAG, "doInBackground: alan :"+alan);
                        Log.d(TAG, "doInBackground: resimYolu"+resimYol);

                    }


//
//                JSONArray jsonarray2 = new JSONArray(json);
//                    for (int i = 0; i < jsonarray2.length(); i++) {
//                        JSONObject jsonobject = jsonarray2.getJSONObject(i);
//
//                        //mekan_id , location,title,description,photo,uye_id,
//                        String ev_id = jsonobject.getString("ev_id");
//                        String ev_il = jsonobject.getString("ev_il");
//                        String ev_emlak_tip = jsonobject.getString("ev_emlak_tip");
//                        String ev_alan = jsonobject.getString("ev_alan");
//                        String ev_oda_sayisi = jsonobject.getString("ev_oda_sayisi");
//                        String ev_bina_yasi = jsonobject.getString("ev_bina_yasi");
//                        String ev_bul_kat = jsonobject.getString("ev_bul_kat");
//                        String ev_fiyat = jsonobject.getString("ev_fiyat");
//                        String ev_aciklama = jsonobject.getString("ev_aciklama");
//                        String ev_resimler = jsonobject.getString("ev_resimler");
//
//
//                        Log.d(TAG, "onPostExecute postId :" + ev_id);
//                        Log.d(TAG, "onPostExecute il  :" + ev_il);
//                        Log.d(TAG, "onPostExecute tip :" + ev_emlak_tip);
//                        Log.d(TAG, "onPostExecute resim :" + ev_resimler);
//
//                        sharedList.add(new HomeItems(ev_id,ev_il,ev_emlak_tip,ev_alan,ev_bina_yasi,ev_oda_sayisi,ev_bul_kat,ev_fiyat,ev_aciklama,ev_resimler));
//                    }
                }catch (Exception e){}
                    Log.d(TAG, "ws do: 5");


            return null;
        }

        @Override
        protected void onPostExecute(String results) {

            Log.d(TAG, "onPostExecute: result" + results);
            Log.d(TAG, "onPostExecute: sharedListSize"+sharedList.size());
            RecyclerViewAdapter adapter_items = new RecyclerViewAdapter(recyclerView,getApplicationContext(),sharedList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter_items);
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


}
