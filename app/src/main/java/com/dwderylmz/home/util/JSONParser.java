package com.dwderylmz.home.util;

/**
 * Created by murat on 23.02.2017.
 */

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class JSONParser {

    private static String TAG ="jsonParser:";
    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray jarray = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public static JSONObject makeHttpRequest(String loginUrl, String method) {
        // making HTTP Request
        try {
            // check for request method
            if(method == "POST"){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(loginUrl);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                Log.d(TAG, "makeHttpRequest: "+httpResponse);

                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else
                // check for request method
                if(method == "GET"){
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(loginUrl);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    Log.d(TAG, "makeHttpRequest httpResponse: "+httpResponse);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();

                    Log.d(TAG, "makeHttpRequest: is:  "+is);
                }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader  reader = new BufferedReader(
                    new InputStreamReader(is, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String  line = null;

            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.d(TAG, "makeHttpRequest: json"+json);
        } catch (IOException e) {
            Log.d("Buffer Error","Error Converting Reesult "+e.toString());
        }

        // try parse the string to JSON Object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error Parsing data" + e.toString());
        }

        // return JSON String
        return jObj;
    }



    public void getJsonData(String type) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("Home");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("DTAG", jo_inside.getString("Task"));
                Log.d("DTAG", jo_inside.getString("Time"));
                Log.d("DTAG", jo_inside.getString("Date"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}