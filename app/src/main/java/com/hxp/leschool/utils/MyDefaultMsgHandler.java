package com.hxp.leschool.utils;

import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.model.operate.ConversationModelOpt;

/**
 * Created by hxp on 17-2-1.
 */

public class MyDefaultMsgHandler extends AVIMMessageHandler {

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if (message instanceof AVIMTextMessage) {
            AVIMTextMessage msg = (AVIMTextMessage) message;
            if (msg.getAttrs() == null) {
                Toast.makeText(MyApplication.getInstance(), "收到新消息1", Toast.LENGTH_SHORT).show();
            } else if (msg.getAttrs().get("TYPE").equals("NEW_FRIEND_REQUEST")) {
                //添加好友请求
                ConversationModel conversationModel = new ConversationModel();
                conversationModel.setType(1);
                conversationModel.setUserName(msg.getFrom());
                conversationModel.setContent(msg.getText());
                conversationModel.setAvatar(R.mipmap.ic_launcher);
                ConversationModelOpt.addConversation(conversationModel);
                Toast.makeText(MyApplication.getInstance(), msg.getFrom() + "加好友消息", Toast.LENGTH_SHORT).show();
            }
            //显示消息通知
        }
    }

    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        Toast.makeText(MyApplication.getInstance(), "收到新消息2", Toast.LENGTH_SHORT).show();
    }
}
