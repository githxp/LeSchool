package com.hxp.leschool.utils;

import android.app.Application;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

/**
 * Created by hxp on 17-1-12.
 */

public class MyApplication extends Application {

    private static MyApplication app;
    private AVIMClient mAVIMClient;
    private String mCurrentChatFriend;

    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        AVOSCloud.initialize(this, "JXors33cW6wDujTiVDgfJh5x-gzGzoHsz", "AgN648cOdXpbA0HHdBJPBXEc");
        AVIMMessageManager.registerDefaultMessageHandler(new MyDefaultMsgHandler());

        connectToServer();
    }


    public AVIMClient getAVIMClient() {
        if (mAVIMClient == null) {
            return null;
        } else {
            return mAVIMClient;
        }
    }

    public void connectToServer() {
        if (AVUser.getCurrentUser() != null) {
            AVIMClient avimClient = AVIMClient.getInstance(AVUser.getCurrentUser().getUsername());
            avimClient.open(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient client, AVIMException e) {
                    if (e == null) {
                        mAVIMClient = client;
                        Toast.makeText(MyApplication.this, AVUser.getCurrentUser().getUsername() + "已连接到服务器", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void setCurrentChatFriend(String currentChatFriend){
        mCurrentChatFriend = currentChatFriend;
    }

    public String getCurrentChatFriend(){
        return mCurrentChatFriend;
    }
}
