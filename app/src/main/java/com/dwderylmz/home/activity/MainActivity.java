package com.dwderylmz.home.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dwderylmz.home.R;
import com.dwderylmz.home.model.HomeItem;
import com.dwderylmz.home.util.Const;
import com.dwderylmz.home.util.DeviceUtils;
import com.dwderylmz.home.util.JSONParser;
import com.dwderylmz.home.util.PreCachingLayoutManager;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "_main";
    private RecyclerView recyclerView;
    private CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_main_recyclerview);
        Log.d(TAG, "onCreate: main____");


        try {
            String ws = new WebService_getPosts(getApplicationContext()).execute(Const.ulrLocal).get();
            Log.d(TAG, "onCreate: next ws"+ws);

        } catch (Exception e) {
            System.err.print(e);
        }
        cardview = (CardView)findViewById(R.id.cardview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //Setup layout manager- for optimization created pre cachehing layout
        PreCachingLayoutManager layoutManager = new PreCachingLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getApplicationContext()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }


    class WebService_getPosts extends AsyncTask<String, String, String> {


        public static final String TAG_SUCCESS = "success";
        public static final String TAG_RESPONSE = "response";
        public static final String ERR_MESSAGE = "error_message";

        ArrayList<HomeItem> sharedList;
        Context ctx;
        private String TAG = "background";
        private List<NameValuePair> nameValuePair;
        // Declare Variables
        JSONObject jsonobject;
        JSONArray jsonArrayPosts;

        // JSON parser Class
        JSONParser jsonParser;
        JSONObject jsonObject;
        ProgressDialog pd = null;

        public WebService_getPosts(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//
           Log.d(TAG, "onPre: url" );
//            pd = new ProgressDialog(ctx);
//            pd.setTitle("Loading...");
//            pd.setMessage("Please wait.");
//            pd.setCancelable(false);
//            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground: Entry");
            String brm_url = params[0];
            jsonParser = new JSONParser();



            Log.d(TAG, "doInBackground: url" + brm_url);

            jsonObject = new JSONObject();
            Log.d(TAG, "ws do: 1");

            try {
                jsonobject = JSONParser.makeHttpRequest(brm_url, "GET");

                Log.d(TAG, "jsonObJect: " + jsonobject);


                    Log.d(TAG, "ws do: 4");
                    jsonArrayPosts = jsonobject.getJSONArray(TAG_RESPONSE);
                    sharedList = new ArrayList<HomeItem>();

                    for (int i = 0; i < jsonArrayPosts.length(); i++) {

                        JSONObject jsonobject = jsonArrayPosts.getJSONObject(i);

                        //mekan_id , location,title,description,photo,uye_id,
                        String ev_id = jsonobject.optString("ev_id");
                        String ev_il = jsonobject.optString("ev_il");
                        String ev_emlak_tip = jsonobject.optString("ev_emlak_tip");
                        String ev_alan = jsonobject.optString("ev_alan");
                        String ev_oda_sayisi = jsonobject.optString("ev_oda_sayisi");
                        String ev_bina_yasi = jsonobject.optString("ev_bina_yasi");
                        String ev_bul_kat = jsonobject.optString("ev_bul_kat");
                        String ev_fiyat = jsonobject.optString("ev_fiyat");
                        String ev_aciklama = jsonobject.optString("ev_aciklama");
                      //  String ev_resimler = jsonobject.optString("ev_resimler");

                        Log.d(TAG, "onPostExecute favorites: postId :" + ev_id);
                        Log.d(TAG, "onPostExecute favorites: postLocation :" + ev_il);
                        Log.d(TAG, "onPostExecute favorites: posttitle :" + ev_emlak_tip);
                      //  Log.d(TAG, "onPostExecute favorites: postDecription :" + ev_resimler);



                     //   sharedList.add(new HomeItem(postId,postPhoto,postUserName,posttitle));

                    }
                    Log.d(TAG, "ws do: 5");

                    Log.d(TAG, "doInBackground: JsonStringData putString: " + jsonobject.toString());


            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {

            Log.d(TAG, "onPostExecute: result" + results);


      //  RecyclerViewAdapter adapter_items = new RecyclerViewAdapter(sharedList,getApplicationContext(),recyclerView);
      //  recyclerView.setHasFixedSize(true);
      //  recyclerView.setAdapter(adapter_items);

//


        }

    }
}
