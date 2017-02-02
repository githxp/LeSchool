package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.AVIMTypedMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.R;
import com.hxp.leschool.databinding.FriendchatAtBinding;
import com.hxp.leschool.utils.MyNormalMsgHandler;
import com.hxp.leschool.utils.publish.ChatMsgPublish;
import com.hxp.leschool.viewmodel.FriendChatViewModel;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatActivity extends Activity {

    private MyNormalMsgHandler mMyNormalMsgHandler = new MyNormalMsgHandler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FriendchatAtBinding friendchatAtBinding = DataBindingUtil.setContentView(this, R.layout.friendchat_at);
        Log.d("fragment", "创建了FriendChatActivity");
        new FriendChatViewModel(this, friendchatAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();

        AVIMMessageManager.unregisterMessageHandler(AVIMTextMessage.class, mMyNormalMsgHandler);
        ChatMsgPublish.removeMyNormalMsgHandlerCallback();

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
}
