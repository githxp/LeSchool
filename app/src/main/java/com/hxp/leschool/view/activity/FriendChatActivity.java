package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.R;
import com.hxp.leschool.databinding.FriendchatAtBinding;
import com.hxp.leschool.utils.MyNormalMsgHandler;
import com.hxp.leschool.utils.event.ChatMsgEvent;
import com.hxp.leschool.viewmodel.FriendChatViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatActivity extends Activity {

    private MyNormalMsgHandler mMyNormalMsgHandler = new MyNormalMsgHandler();
    private FriendChatViewModel mFriendChatViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FriendchatAtBinding friendchatAtBinding = DataBindingUtil.setContentView(this, R.layout.friendchat_at);
        Log.d("fragment", "创建了FriendChatActivity");
        mFriendChatViewModel = new FriendChatViewModel(this, friendchatAtBinding);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("Fragment", "onPause()触发-FriendChatActivity");
        Log.d("Fragment", "EventBus注册");
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        Log.d("Fragment", "onPause()触发-FriendChatActivity");
        Log.d("Fragment", "EventBus解除注册");
    }

    @Override
    public void onPause() {
        super.onPause();
        AVIMMessageManager.unregisterMessageHandler(AVIMTextMessage.class, mMyNormalMsgHandler);
        Log.d("Fragment生命周期管理", "onPause()触发-FriendChatActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        AVIMMessageManager.registerMessageHandler(AVIMTextMessage.class, mMyNormalMsgHandler);
        Log.d("Fragment生命周期管理", "onResume()触发-FriendChatActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-FriendChatActivity");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleChatMsgEvent(ChatMsgEvent chatMsgEvent) {
        Log.d("fragment", "新消息处理" + chatMsgEvent.getMsg());
        //处理新消息
        mFriendChatViewModel.handleChatMsgEvent(chatMsgEvent);
    }
}
