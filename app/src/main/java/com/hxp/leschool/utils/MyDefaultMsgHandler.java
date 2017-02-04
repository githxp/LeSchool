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
import com.hxp.leschool.widget.MyNotification;

import java.util.ArrayList;

/**
 * 全局消息打开通知
 * Created by hxp on 17-2-1.
 */

public class MyDefaultMsgHandler extends AVIMMessageHandler {

    private ArrayList<String> msgSender = new ArrayList<>();
    private int msgNum;//消息数量

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if (message instanceof AVIMTextMessage) {//处理文本消息
            AVIMTextMessage msg = (AVIMTextMessage) message;
            msgNum++;
            if (msg.getAttrs() == null) {//普通消息
                if (msgSender.size() == 0) {
                    msgSender.add(msg.getFrom());
                } else if (msg.getFrom().equals(msgSender.get(0)) && msgSender.size() == 1) {//同一个消息来源
                    new MyNotification(msg.getFrom(), R.mipmap.ic_launcher, msg.getText(), 100);
                } else {//非同一个消息来源
                    if (!msg.getFrom().equals(msgSender.get(msgSender.size() - 1))) {
                        msgSender.add(msg.getFrom());
                    }
                    new MyNotification("您有多条消息", R.mipmap.ic_launcher, "有" + msgSender.size() + "个联系人给您发来" + msgNum + "条消息", 100);
                }
            } else if (msg.getAttrs().get("TYPE").equals("NEW_FRIEND_REQUEST")) {//添加好友请求
                ConversationModel conversationModel = new ConversationModel();
                conversationModel.setType(1);
                conversationModel.setUserName(msg.getFrom());
                conversationModel.setMsg(msg.getText());
                conversationModel.setAvatar(R.mipmap.ic_launcher);
                ConversationModelOpt.addConversation(conversationModel);
                Toast.makeText(MyApplication.getInstance(), "来自" + msg.getFrom() + "的加好友消息", Toast.LENGTH_SHORT).show();
            }
            //显示消息通知
        }
    }

    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        Toast.makeText(MyApplication.getInstance(), "收到新消息2", Toast.LENGTH_SHORT).show();
    }
}
