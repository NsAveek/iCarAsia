package com.icarasia.sample.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.icarasia.sample.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private int TIME_OUT = 5000;
    private int imageList[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        initHandler();
    }
    private void init(){
        imageView =(ImageView) findViewById(R.id.imgV_random) ;
        imageList = new int[]{R.drawable.dominow,R.drawable.dvb,R.drawable.fb,R.drawable.linkedin,R.drawable.ncc,R.drawable.twitter};

    }
    private int getRandomImageFromList(){
            int rnd = new Random().nextInt(imageList.length);
            return imageList[rnd];
    }

    private void initHandler(){

        new CountDownTimer(TIME_OUT, 1000) {
            public void onTick(long millisUntilFinished) {
                imageView.setBackgroundResource(getRandomImageFromList());
            }

            public void onFinish() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

        }.start();
    }
}
