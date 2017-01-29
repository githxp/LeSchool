package com.hxp.leschool.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import com.hxp.leschool.adapter.FriendChatAdapter;
import com.hxp.leschool.databinding.FriendchatAtBinding;
import com.hxp.leschool.model.operate.FriendChatModelOpt;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.model.operate.FriendChatModelOpt.FriendChatSendMsgCallback;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatViewModel implements FriendChatSendMsgCallback {

    public FriendChatModelOpt mFriendChatModelOpt;
    private FriendChatActivity mFriendChatActivity;
    private FriendchatAtBinding mFriendchatAtBinding;
    private FriendChatAdapter mFriendChatAdapter;

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

    }

    public void onFriendChat_Layout_SendMsgClicked(View view) {

    }

    @Override
    public void friendChatSendMsgCompleted() {
        mFriendChatAdapter.notifyDataSetChanged();
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调接收方");
    }
}
