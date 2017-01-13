package com.hxp.leschool.utils;

import android.app.Application;

/**
 * Created by hxp on 17-1-12.
 */

public class MyApplication extends Application {

    private static MyApplication app;
    public static MyApplication getInstance(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
