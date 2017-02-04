package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.databinding.SendaddreqAtBinding;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.SendAddReqActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hxp on 17-1-22.
 */

public class SendAddReqViewModel {

    private SendAddReqActivity mSendAddReqActivity;
    private SendaddreqAtBinding mSendaddreqAtBinding;

    public SendAddReqViewModel(SendAddReqActivity sendAddReqActivity, SendaddreqAtBinding sendaddreqAtBinding) {

        Log.d("fragment", "SendAddReqViewModel创建");
        mSendAddReqActivity = sendAddReqActivity;
        mSendaddreqAtBinding = sendaddreqAtBinding;

        mSendaddreqAtBinding.setMSendAddReqViewModel(this);
    }

    public void btn_SendAddReq_send(View view) {
        final String reqMsg = mSendaddreqAtBinding.etSendAddReqReqMsg.getText().toString();
        if (reqMsg.equals("")) {
            Toast.makeText(mSendAddReqActivity, "请输入请求消息", Toast.LENGTH_SHORT).show();
        } else {
            String userName = mSendAddReqActivity.getIntent().getStringExtra("userName");
            Log.d("fragment", "username:" + userName);
            MyApplication.getInstance().getAVIMClient().createConversation(Arrays.asList(userName), userName + "&" + AVUser.getCurrentUser().getUsername(), null, new AVIMConversationCreatedCallback() {
                @Override
                public void done(AVIMConversation avimConversation, AVIMException e) {
                    if (e == null) {
                        AVIMTextMessage msg = new AVIMTextMessage();
                        msg.setText(reqMsg);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("TYPE", "NEW_FRIEND_REQUEST");
                        msg.setAttrs(map);
                        avimConversation.sendMessage(msg, new AVIMConversationCallback() {
                            @Override
                            public void done(AVIMException e) {
                                if (e == null) {
                                    Toast.makeText(mSendAddReqActivity, "请求发送成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Log.d("fragment", "创建会话失败" + e.getMessage());
                    }
                }
            });
        }
    }
}
