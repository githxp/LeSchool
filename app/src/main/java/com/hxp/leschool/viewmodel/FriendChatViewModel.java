package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
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
    private AVIMTextMessage mAVIMTextMessage;
    private AVIMConversation mAVIMConversation;

    public FriendChatViewModel(FriendChatActivity friendChatActivity, FriendchatAtBinding friendchatAtBinding) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mFriendChatActivity = friendChatActivity;
        mFriendchatAtBinding = friendchatAtBinding;

        mFriendChatModelOpt = new FriendChatModelOpt(this);
        mFriendChatAdapter = new FriendChatAdapter(this);

        mFriendchatAtBinding.setMFriendChatViewModel(this);

        mFriendchatAtBinding.rvMicroblogSingleChatMessage.setLayoutManager(new LinearLayoutManager(mFriendChatActivity, LinearLayoutManager.VERTICAL, false));
        mFriendchatAtBinding.rvMicroblogSingleChatMessage.setAdapter(mFriendChatAdapter);

        mFriendChatAdapter.setOnItemClickListener(new FriendChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //聊天消息列表点击事件
            }
        });

        initChat();
    }

    public void initChat() {
        AVIMClient aVIMClient = AVIMClient.getInstance(AVUser.getCurrentUser().getUsername());
        aVIMClient.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    String chatFriend = mFriendChatActivity.getIntent().getStringExtra("chatFriend");
                    Log.d("fragment", "好友校验2：" + chatFriend);
                    client.createConversation(Arrays.asList(chatFriend), null, new AVIMConversationCreatedCallback() {
                        @Override
                        public void done(AVIMConversation conversation, AVIMException e) {
                            if (e == null) {
                                //会话创建完成
                                mAVIMConversation = conversation;
                            } else {
                                Toast.makeText(mFriendChatActivity, "创建对话错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(mFriendChatActivity, "服务器链接错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onFriendChat_Layout_SendMsgClicked(View view) {
        final String message = mFriendchatAtBinding.etMicroblogSingleChatMessage.getText().toString();
        if (!message.equals("")) {
            if (mAVIMTextMessage == null) {
                mAVIMTextMessage = new AVIMTextMessage();
            }
            mAVIMTextMessage.setText(message);
            mAVIMConversation.sendMessage(mAVIMTextMessage, new AVIMConversationCallback() {
                @Override
                public void done(AVIMException e) {
                    if (e == null) {
                        mFriendChatModelOpt.setData(message);
                        Log.d("fragment", "发送成功！");
                    } else {
                        Toast.makeText(mFriendChatActivity, "消息发送失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void friendChatSendMsgCompleted() {
        mFriendChatAdapter.notifyDataSetChanged();
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调接收方");
    }
}
