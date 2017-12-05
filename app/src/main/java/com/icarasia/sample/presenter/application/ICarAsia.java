package com.icarasia.sample.presenter.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Aveek on 05/12/2017.
 */

public class ICarAsia extends Application {

    private static ICarAsia sInstance;

    public ICarAsia(){

    }

    public static synchronized ICarAsia getInstance() {

        if (sInstance == null)
            sInstance = new ICarAsia();
        return sInstance;
    }

    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initializeInstance();

        Stetho.initializeWithDefaults(this);
        initRealm();

    }

    private void initializeInstance() {
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("icarasia.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
