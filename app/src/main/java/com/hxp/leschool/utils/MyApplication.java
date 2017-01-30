package com.hxp.leschool.utils;

import android.app.Application;

import com.hxp.leschool.viewmodel.MineUserInfoViewModel;
import com.hxp.leschool.viewmodel.MineUserInfoViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

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

        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            //NewIM初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new BmobMsgHandler(this));
        }
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

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
