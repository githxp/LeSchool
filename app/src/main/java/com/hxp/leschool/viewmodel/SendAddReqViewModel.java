package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.databinding.SendaddreqAtBinding;
import com.hxp.leschool.model.operate.SendAddReqModelOpt;
import com.hxp.leschool.model.operate.SendAddReqModelOpt.SendAddReqOptCallback;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.SendAddReqActivity;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by hxp on 17-1-22.
 */

public class SendAddReqViewModel implements SendAddReqOptCallback {

    private SendAddReqModelOpt mSendAddReqModelOpt;
    private SendAddReqActivity mSendAddReqActivity;
    private SendaddreqAtBinding mSendaddreqAtBinding;

    public SendAddReqViewModel(SendAddReqActivity sendAddReqActivity, SendaddreqAtBinding sendaddreqAtBinding) {

        Log.d("fragment", "SendAddReqViewModel创建");
        mSendAddReqActivity = sendAddReqActivity;
        mSendaddreqAtBinding = sendaddreqAtBinding;

        mSendAddReqModelOpt = new SendAddReqModelOpt(this);

        mSendaddreqAtBinding.setMSendAddReqViewModel(this);
    }

    public void onSendAddReq_Layout_SendAddReqClicked(View view) {
        String reqMsg = mSendaddreqAtBinding.etSendAddReqReqMsg.getText().toString();
        if (reqMsg.equals("")) {
            reqMsg = "请加我为好友吧";
            Log.d("fragment", "默认添加好友请求");
        }
        String userName = mSendAddReqActivity.getIntent().getStringExtra("userName");
        String userID = mSendAddReqActivity.getIntent().getStringExtra("userID");
        String avatar = mSendAddReqActivity.getIntent().getStringExtra("avatar");
        Log.d("fragment", "username:" + userName + " userid:" + userID + " avatar:" + avatar);
        BmobIMUserInfo bmobIMUserInfo = new BmobIMUserInfo(userID, userName, avatar);
        BmobIMConversation bmobIMConversation = BmobIM.getInstance().startPrivateConversation(bmobIMUserInfo, true, null);
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), bmobIMConversation);
        BmobIMTextMessage msg =new BmobIMTextMessage();
        msg.setContent("ok");
        Map<String, Object> map = new HashMap<>();
        MyUser myUser = BmobUser.getCurrentUser(MyApplication.getInstance(),MyUser.class);
        map.put("name", myUser.getUsername());//发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
        map.put("avatar", myUser.getAvatar());//发送者的头像
        map.put("uid", myUser.getObjectId());//发送者的uid
        msg.setExtraMap(map);
        Log.d("fragmnt", "请求内容:" + reqMsg);
        Log.d("fragmnt", myUser.getUsername() + " " + myUser.getAvatar() + " " + myUser.getObjectId());
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "好友请求发送成功，等待验证");
                } else {
                    Log.d("fragment", "发送失败:" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void sendAddReqSucceedCompleted() {
        Log.d("fragment", "SearchFriendModelOpt获取数据回调接收方");

    }

    @Override
    public void sendAddReqFailedCompleted() {

    }
}
