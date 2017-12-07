package com.icarasia.sample.splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.icarasia.sample.R;
import com.icarasia.sample.login.LoginActivity;

import java.util.Random;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    private ImageView imageView;

    private int imageList[];
    private ISplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        presenter = new SplashPresenterImpl(this);
        imageView = findViewById(R.id.imgV_random);
        imageList = new int[]{R.drawable.dominow, R.drawable.dvb, R.drawable.fb, R.drawable.linkedin, R.drawable.ncc, R.drawable.twitter};
        presenter.initHandler(imageList);
    }
    @Override
    public void setupImageBackground(int id){
        imageView.setBackgroundResource(id);
    }
    @Override
    public void finishActivity(){
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
