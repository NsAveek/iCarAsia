package com.icarasia.sample.main;

import android.os.CountDownTimer;

import com.icarasia.sample.model.User;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Aveek on 06/12/2017.
 */

public class MainModel implements IMainModel {

    private Realm realm;
    private int TIME_OUT = 5000;

    public MainModel(){
        realm = Realm.getDefaultInstance();
    }

    @Override
    public User getUserInfo(String email) {
        try {
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            RealmResults<User> userRealmResults = realm.where(User.class).findAll();
            for (User result : userRealmResults) {
                if (result.getEmail().equals(email)) {
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUserMobileModel(String emailId, String newMobileNumber) throws Exception {
        try {
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            User userRealmResults = realm.where(User.class).equalTo("email", emailId).findFirst();
            userRealmResults.setMobileNumber(newMobileNumber);
            realm.copyToRealmOrUpdate(userRealmResults);
            realm.commitTransaction();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void logoutModel(final IMainModel.OnFinishedListener listener) {
        new CountDownTimer(TIME_OUT, 1000) {
            public void onTick(long millisUntilFinished) {
                listener.onRunning();
            }
            public void onFinish() {
                listener.onFinished();
            }

        }.start();
    }
}
