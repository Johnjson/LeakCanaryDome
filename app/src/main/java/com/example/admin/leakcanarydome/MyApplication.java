package com.example.admin.leakcanarydome;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by admin on 16/12/19.
 */
public class MyApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

}
