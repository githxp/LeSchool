package com.hxp.leschool.utils;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.view.activity.MainActivity;
import com.hxp.leschool.viewmodel.MineUserInfoViewModel;

/**
 * Created by hxp on 17-1-12.
 */

public class MyApplication extends Application {

    private static MyApplication app;

    private LoginSucceedCallback mLoginSucceedCallback;
    private LogoutSucceedCallback mLogoutSucceedCallback;
    private MicroblogSingleChatCallback mMicroblogSingleChatCallback;

    public static class CustomMessageHandler extends AVIMMessageHandler {
        @Override
        public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
            if (message instanceof AVIMTextMessage) {
                //接收到消息后的处理逻辑
                Log.d("接收到消息", ((AVIMTextMessage) message).getText());
            }
        }

        public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {

        }
    }

    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AVOSCloud.initialize(this, "JXors33cW6wDujTiVDgfJh5x-gzGzoHsz", "AgN648cOdXpbA0HHdBJPBXEc");
        AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());
    }

    public void setMineUserInfoViewModel(MineUserInfoViewModel mineUserInfoViewModel) {
        mLoginSucceedCallback = mineUserInfoViewModel;
        mLogoutSucceedCallback = mineUserInfoViewModel;
    }

    public void setMainActivity(MainActivity mainActivity) {
        mMicroblogSingleChatCallback = mainActivity;
    }

    public interface LoginSucceedCallback {
        void loginSucceedCallback();
    }

    public interface LogoutSucceedCallback {
        void logoutSucceedCallback();
    }

    public interface MicroblogSingleChatCallback {
        void microblogSingleChatCallback(String userName);
    }

    public LoginSucceedCallback getLoginSucceedCallback() {
        return mLoginSucceedCallback;
    }

    public LogoutSucceedCallback getLogoutSucceedCallback() {
        return mLogoutSucceedCallback;
    }

    public MicroblogSingleChatCallback getMicroblogSingleChatCallback() {
        return mMicroblogSingleChatCallback;
    }
}
