package com.dwderylmz.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.dwderylmz.home.R;

/**
 * Created by macbookpro on 4.05.2017.
 */

public class InternetClosedActivity extends Activity{
    private Button btnYenidenDene;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_closed);

        btnYenidenDene = (Button)findViewById(R.id.btnYenidenDeneyin);

        btnYenidenDene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
