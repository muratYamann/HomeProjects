package com.dwderylmz.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dwderylmz.home.R;

import java.util.ArrayList;

/**
 * Created by macbookpro on 19.05.2017.
 */

public class DetailActivity extends Activity  {

    ArrayList<String> itemTitles;
    ArrayList<String>itemImageUrl;
    Bundle bundle;
    TextView tvData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvData = (TextView)findViewById(R.id.tvData);

        bundle  = getIntent().getExtras();
        String data = bundle.getString("link");

        tvData.setText(data);


    }

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
