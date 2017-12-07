package com.icarasia.sample.splash;

import android.content.Intent;
import android.os.CountDownTimer;

import com.icarasia.sample.login.LoginActivity;

import java.util.Random;

/**
 * Created by Aveek on 08/12/2017.
 */

public class SplashPresenterImpl implements ISplashPresenter{

    private ISplashView splashView;
    private int TIME_OUT = 5000;
    private int imageList[];

    public SplashPresenterImpl(ISplashView splashView){
        this.splashView = splashView;
    }

    private int getRandomImageFromList() {
        int rnd = new Random().nextInt(imageList.length);
        return imageList[rnd];
    }
    public void initHandler(int[]imageList) {
        this.imageList = imageList;
        new CountDownTimer(TIME_OUT, 1000) {
            public void onTick(long millisUntilFinished) {
                splashView.setupImageBackground(getRandomImageFromList());
            }
            public void onFinish() {
                splashView.finishActivity();
            }
        }.start();
    }
}
