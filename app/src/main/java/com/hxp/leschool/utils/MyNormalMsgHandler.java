package com.hxp.leschool.utils;

import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.utils.publish.ChatMsgPublish;

/**
 * Created by hxp on 17-2-1.
 */

public class MyNormalMsgHandler extends AVIMMessageHandler {

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if (message instanceof AVIMTextMessage) {
            MyNormalMsgHandlerCallback mMyNormalMsgHandlerCallback = ChatMsgPublish.getMyNormalMsgHandlerCallback();
            if (mMyNormalMsgHandlerCallback != null) {
                Log.d("fragment", "mMyNormalMsgHandlerCallback通知内存地址：" + mMyNormalMsgHandlerCallback.toString());
                mMyNormalMsgHandlerCallback.receNewMsg(((AVIMTextMessage) message).getText());
            }
            Toast.makeText(MyApplication.getInstance(), "收到新消息", Toast.LENGTH_SHORT).show();
        }
    }

    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        Toast.makeText(MyApplication.getInstance(), "消息已送达", Toast.LENGTH_SHORT).show();
    }

    public interface MyNormalMsgHandlerCallback {
        void receNewMsg(String msg);

        void sendMsgSucceed();
    }
}
