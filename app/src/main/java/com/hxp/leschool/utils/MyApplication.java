package com.hxp.leschool.utils;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;

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
        AVOSCloud.initialize(this, "JXors33cW6wDujTiVDgfJh5x-gzGzoHsz", "AgN648cOdXpbA0HHdBJPBXEc");
    }
}
