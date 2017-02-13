package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginandregAtBinding;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.LoginSwitchRegEvent;
import com.hxp.leschool.utils.event.RegSwitchLoginEvent;
import com.hxp.leschool.viewmodel.LoginAndRegViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * Created by hxp on 17-1-19.
 */


public class LoginAndRegActivity extends Activity {

    private LoginAndRegViewModel mLoginAndRegViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginandregAtBinding loginandregAtBinding = DataBindingUtil.setContentView(this, R.layout.loginandreg_at);
        Log.d("fragment", "创建了LoginAndRegActivity");
        mLoginAndRegViewModel = new LoginAndRegViewModel(this, loginandregAtBinding);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("Fragment", "onPause()触发-LoginAndRegActivity");
        Log.d("Fragment", "EventBus注册");
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        Log.d("Fragment", "onPause()触发-LoginAndRegActivity");
        Log.d("Fragment", "EventBus解除注册");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-LoginAndRegActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-LoginAndRegActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-LoginAndRegActivity");
    }

    @Subscribe
    public void handleLoginSwitchRegEvent(LoginSwitchRegEvent loginSwitchRegEvent) {
        Log.d("fragment", "登陆切换到注册处理" + "conversationEvent");
        mLoginAndRegViewModel.handleLoginSwitchRegEvent(loginSwitchRegEvent);
    }

    @Subscribe
    public void handleRegSwitchLoginEvent(RegSwitchLoginEvent regSwitchLoginEvent) {
        Log.d("fragment", "注册切换到登陆处理" + "conversationEvent");
        mLoginAndRegViewModel.handleRegSwitchLoginEvent(regSwitchLoginEvent);
    }

}
