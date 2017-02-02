package com.hxp.leschool.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.adapter.FriendChatAdapter;
import com.hxp.leschool.databinding.FriendchatAtBinding;
import com.hxp.leschool.model.operate.FriendChatModelOpt;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.model.operate.FriendChatModelOpt.FriendChatSendMsgCallback;

import java.util.Arrays;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatViewModel implements FriendChatSendMsgCallback {

    public FriendChatModelOpt mFriendChatModelOpt;
    private FriendChatActivity mFriendChatActivity;
    private FriendchatAtBinding mFriendchatAtBinding;
    private FriendChatAdapter mFriendChatAdapter;
    private AVIMConversation mAvimConversation;

    public FriendChatViewModel(FriendChatActivity friendChatActivity, FriendchatAtBinding friendchatAtBinding) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mFriendChatActivity = friendChatActivity;
        mFriendchatAtBinding = friendchatAtBinding;

        mFriendChatModelOpt = new FriendChatModelOpt(this);
        mFriendChatAdapter = new FriendChatAdapter(this);

        mFriendchatAtBinding.setMFriendChatViewModel(this);

        mFriendchatAtBinding.rvFriendChatMsg.setLayoutManager(new LinearLayoutManager(mFriendChatActivity, LinearLayoutManager.VERTICAL, false));
        mFriendchatAtBinding.rvFriendChatMsg.setAdapter(mFriendChatAdapter);

        mFriendChatAdapter.setOnItemClickListener(new FriendChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //聊天消息列表点击事件
            }
        });

        initChat();
    }

    public void initChat() {
        String userName = mFriendChatActivity.getIntent().getStringExtra("userName");
        Log.d("fragment", "与" + userName + "建立了会话");
        MyApplication.getInstance().getAVIMClient().createConversation(Arrays.asList(userName), userName + "&" + AVUser.getCurrentUser().getUsername(), null, new AVIMConversationCreatedCallback() {
            @Override
            public void done(AVIMConversation avimConversation, AVIMException e) {
                mAvimConversation = avimConversation;
            }
        });
    }

    public void onFriendChat_Layout_SendMsgClicked(View view) {
        final AVIMTextMessage msg = new AVIMTextMessage();
        msg.setText(mFriendchatAtBinding.etFriendChatMsg.getText().toString());
        mAvimConversation.sendMessage(msg, new AVIMConversationCallback() {
            @Override
            public void done(AVIMException e) {
                mFriendChatModelOpt.setMsg(msg.getText(), true);
            }
        });
    }

    @Override
    public void friendChatSendMsgCompleted() {
        mFriendChatAdapter.notifyDataSetChanged();
        Log.d("fragment", "FriendChatModelOpt消息发送成功回调接收方");
    }
}
