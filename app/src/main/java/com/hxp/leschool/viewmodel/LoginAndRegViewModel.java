package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginandregAtBinding;
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
            Log.d("Fragment生命周期管理", "创建LoginFragment");
        }
        mLoginAndRegActivity.getFragmentManager().beginTransaction().add(R.id.ll_mine_loginAndReg_content, mLoginFragment).commit();
    }

    public void onLoginAndReg_Layout_LoginClicked(View view) {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
            Log.d("Fragment生命周期管理", "创建LoginFragment");
        }
        if (!mLoginFragment.isVisible()) {
            mLoginAndRegActivity.getFragmentManager().beginTransaction().replace(R.id.ll_mine_loginAndReg_content, mLoginFragment).commit();
        }
    }

    public void onLoginAndReg_Layout_RegClicked(View view) {
        if (mRegFragment == null) {
            mRegFragment = new RegFragment();
            Log.d("Fragment生命周期管理", "创建RegisterFragment");
        }
        if (!mRegFragment.isVisible()) {
            mLoginAndRegActivity.getFragmentManager().beginTransaction().replace(R.id.ll_mine_loginAndReg_content, mRegFragment).commit();
        }
    }
}
