package com.dwderylmz.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwderylmz.home.R;


/**
 * Created by Murat on.
 */

public class SplashScreen extends AppCompatActivity {

    private  static String TAG = "Splash";
    ImageView logo;
    TextView appName;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        Thread mSplashThread;


        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(4000);
                    }

                    try {
                      //  if (!email.equals("") && !password.equals("")) {
                            startActivity(new Intent(SplashScreen.this, MainActivity.class));
                            finish();
                            System.exit(1);
                            System.gc();

                      //  } else {
                           // startActivity(new Intent(SplashScreen.this, SignIn.class));

                      //  }
                    }catch (Exception e){}

                } catch (InterruptedException ex) {
                }

            }
        };
        mSplashThread.start();

    }

    }

