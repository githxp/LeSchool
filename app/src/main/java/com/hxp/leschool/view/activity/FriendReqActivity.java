package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.R;
import com.hxp.leschool.databinding.FriendchatAtBinding;
import com.hxp.leschool.databinding.FriendreqAtBinding;
import com.hxp.leschool.utils.MyNormalMsgHandler;
import com.hxp.leschool.utils.publish.ChatMsgPublish;
import com.hxp.leschool.viewmodel.FriendChatViewModel;
import com.hxp.leschool.viewmodel.FriendReqViewModel;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendReqActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FriendreqAtBinding friendreqAtBinding = DataBindingUtil.setContentView(this, R.layout.friendreq_at);
        Log.d("fragment", "创建了FriendReqActivity");
        new FriendReqViewModel(this, friendreqAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-FriendReqActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-FriendReqActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-FriendReqActivity");
    }
}
