package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginandregAtBinding;
import com.hxp.leschool.utils.event.LoginSwitchRegEvent;
import com.hxp.leschool.utils.event.RegSwitchLoginEvent;
import com.hxp.leschool.view.activity.LoginAndRegActivity;
import com.hxp.leschool.view.fragment.LoginFragment;
import com.hxp.leschool.view.fragment.RegFragment;


/**
 * Created by hxp on 17-1-20.
 */


public class LoginAndRegViewModel {

    private LoginAndRegActivity mLoginAndRegActivity;
    private LoginandregAtBinding mLoginandregAtBinding;
    private LoginFragment mLoginFragment;
    private RegFragment mRegFragment;

    public LoginAndRegViewModel(LoginAndRegActivity loginAndRegActivity, LoginandregAtBinding loginandregAtBinding) {

        mLoginAndRegActivity = loginAndRegActivity;
        mLoginandregAtBinding = loginandregAtBinding;

        mLoginandregAtBinding.setMLoginAndRegViewModel(this);
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
            Log.d("Fragment", "创建LoginFragment");
        }
        mLoginAndRegActivity.getFragmentManager().beginTransaction().add(R.id.ll_loginAndReg_content, mLoginFragment).commit();
    }

    public void handleRegSwitchLoginEvent(RegSwitchLoginEvent regSwitchLoginEvent) {
        mLoginAndRegActivity.getFragmentManager().beginTransaction().hide(mRegFragment).commit();
        mLoginAndRegActivity.getFragmentManager().beginTransaction().show(mLoginFragment).commit();
    }

    public void handleLoginSwitchRegEvent(LoginSwitchRegEvent loginSwitchRegEvent) {
        if (mRegFragment == null) {
            mRegFragment = new RegFragment();
            Log.d("Fragment", "创建RegisterFragment");
            mLoginAndRegActivity.getFragmentManager().beginTransaction().hide(mLoginFragment).commit();
            mLoginAndRegActivity.getFragmentManager().beginTransaction().add(R.id.ll_loginAndReg_content, mRegFragment).commit();
        } else {
            Log.d("Fragment", "显示RegisterFragment");
            mLoginAndRegActivity.getFragmentManager().beginTransaction().hide(mLoginFragment).commit();
            mLoginAndRegActivity.getFragmentManager().beginTransaction().show(mRegFragment).commit();
        }
    }
}
