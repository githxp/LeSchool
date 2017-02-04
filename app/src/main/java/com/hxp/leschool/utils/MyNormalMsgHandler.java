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
import com.hxp.leschool.utils.event.NewMsgEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hxp on 17-2-1.
 */

public class MyNormalMsgHandler extends AVIMMessageHandler {

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if (message instanceof AVIMTextMessage) {
            AVIMTextMessage msg = (AVIMTextMessage) message;
            if (msg.getAttrs() == null) {
                EventBus.getDefault().post(new NewMsgEvent(msg.getText()));
            } else if (msg.getAttrs().get("TYPE").equals("NEW_FRIEND_REQUEST")) {
                //添加好友请求
                ConversationModel conversationModel = new ConversationModel();
                conversationModel.setType(1);
                conversationModel.setUserName(msg.getFrom());
                conversationModel.setMsg(msg.getText());
                conversationModel.setAvatar(R.mipmap.ic_launcher);
                ConversationModelOpt.addConversation(conversationModel);
                Toast.makeText(MyApplication.getInstance(), "来自" + msg.getFrom() + "的加好友请求", Toast.LENGTH_SHORT).show();
            }
            //显示消息通知
        }
    }

    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
    }
}
