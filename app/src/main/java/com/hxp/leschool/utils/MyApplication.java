package com.hxp.leschool.utils;

import android.app.Application;

import com.hxp.leschool.viewmodel.MineUserInfoViewModel;
import com.hxp.leschool.viewmodel.MineUserInfoViewModel;

/**
 * Created by hxp on 17-1-12.
 */

public class MyApplication extends Application {

    private static MyApplication app;

    private LoginAndRegCallback mLoginAndRegCallback;

    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public void setMineUserInfoViewModel(MineUserInfoViewModel mineUserInfoViewModel) {
        mLoginAndRegCallback = mineUserInfoViewModel;
    }

    public interface LoginAndRegCallback {
        void loginSucceedCallback();

        void logoutSucceedCallback();
    }

    public LoginAndRegCallback getLoginAndRegCallback() {
        return mLoginAndRegCallback;
    }
}
