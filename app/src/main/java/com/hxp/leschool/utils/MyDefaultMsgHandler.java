package com.hxp.leschool.utils;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.R;
import com.hxp.leschool.model.db.bean.opt.ConversationBeanOpt;
import com.hxp.leschool.widget.MyNotification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * 全局消息打开通知
 * Created by hxp on 17-2-1.
 */

public class MyDefaultMsgHandler extends AVIMMessageHandler {

    private ArrayList<String> msgSender = new ArrayList<>();

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if (message instanceof AVIMTextMessage) {//处理文本消息
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            String lastTime = simpleDateFormat.format(new Date(message.getTimestamp()));
            AVIMTextMessage msg = (AVIMTextMessage) message;
            Map<String, Object> attrs = msg.getAttrs();
            if (attrs == null) {//普通消息
                ConversationBeanOpt.getInstance().changeORadd(message.getFrom(), msg.getText(), lastTime, 1, 2);
            } else if (attrs.get("TYPE").equals("NEW_FRIEND_REQUEST")) {//加好友消息
                ConversationBeanOpt.getInstance().changeORadd(message.getFrom(), msg.getText(), lastTime, 1, 1);
            }
            new MyNotification(msg.getFrom(), R.mipmap.ic_launcher, msg.getText(), 100);
        }
    }

    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
    }
}
