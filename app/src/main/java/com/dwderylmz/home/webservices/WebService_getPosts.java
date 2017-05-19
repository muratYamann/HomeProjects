package com.dwderylmz.home.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dwderylmz.home.util.JSONParser;
import com.dwderylmz.home.model.HomeItem;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 17.05.2017.
 */

class WebService_getPosts extends AsyncTask<String ,String,String> {



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
    JSONParser jsonParser ;
    JSONObject jsonObject;
    ProgressDialog pd = null;




    public WebService_getPosts(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String brm_url = params[0];
        String last_index = params[1];
        jsonParser = new JSONParser();

        Log.d(TAG, "doInBackground: url"+brm_url);

        jsonObject = new JSONObject();
        Log.d(TAG, "ws do: 1");

        try {


            jsonobject = JSONParser.makeHttpRequest(brm_url,"GET");

            Log.d(TAG, "jsonObJect: "+jsonobject);

            if(jsonobject.getBoolean(TAG_SUCCESS))
            {

                Log.d(TAG, "ws do: 4");
                jsonArrayPosts = jsonobject.getJSONArray(TAG_RESPONSE);
                sharedList = new ArrayList<HomeItem>();

                for (int i = 0; i < jsonArrayPosts.length(); i++) {

                    JSONObject jsonobject = jsonArrayPosts.getJSONObject(i);

                    //mekan_id , location,title,description,photo,uye_id,
                    String postId            = jsonobject.optString("mekan_id") ;
                    String postLocation      = jsonobject.optString("mekan_location") ;
                    String posttitle         = jsonobject.optString("mekan_title") ;
                    String postDecription    = jsonobject.optString("mekan_description") ;
                    String postPhoto         = jsonobject.optString("mekan_photo") ;

                       //String postDate          = jsonobject.optString("mekan_date");

                    Log.d(TAG, "onPostExecute favorites: postId :"+postId);
                    Log.d(TAG, "onPostExecute favorites: postLocation :"+postLocation);
                    Log.d(TAG, "onPostExecute favorites: posttitle :"+posttitle);
                    Log.d(TAG, "onPostExecute favorites: postDecription :"+postDecription);

                    //sharedList.add(new HomeItem(postId,postUserID,postUserName ,postLocation));

                }
                Log.d(TAG, "ws do: 5");

                Log.d(TAG, "doInBackground: JsonStringData putString: "+jsonobject.toString());


            }else{
                Log.d(TAG, "doInBackground: failure");
                Log.d(TAG, "doInBackground: failute"+jsonobject.getString(ERR_MESSAGE));
            }
            Log.d(TAG, "ws do: 6");

        } catch (JSONException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String results) {

        Log.d(TAG, "onPostExecute: result"+results);
        if (pd != null) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            pd = null;
        }

//        HomeRecyclerViewAdapter adapter_items = new HomeRecyclerViewAdapter(sharedList,root,recyclerViewHome);
//        recyclerViewHome.setHasFixedSize(true);
//        recyclerViewHome.setAdapter(adapter_items);
//
//



    }
}

