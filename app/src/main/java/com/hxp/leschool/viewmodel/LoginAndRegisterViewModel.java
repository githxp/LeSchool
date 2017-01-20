package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginandregisterFmBinding;
import com.hxp.leschool.view.fragment.LoginAndRegisterFragment;
import com.hxp.leschool.view.fragment.LoginFragment;
import com.hxp.leschool.view.fragment.RegisterFragment;

/**
 * Created by hxp on 17-1-20.
 */

public class LoginAndRegisterViewModel {

    private LoginAndRegisterFragment mLoginAndRegisterFragment;
    private LoginandregisterFmBinding mLoginandregisterFmBinding;
    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;

    public LoginAndRegisterViewModel(LoginAndRegisterFragment loginAndRegisterFragment, LoginandregisterFmBinding loginandregisterFmBinding) {

        mLoginAndRegisterFragment = loginAndRegisterFragment;
        mLoginandregisterFmBinding = loginandregisterFmBinding;

        mLoginandregisterFmBinding.setMLoginAndRegisterViewModel(this);

        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
            Log.d("Fragment生命周期管理", "创建LoginFragment");
        }
        mLoginAndRegisterFragment.getChildFragmentManager().beginTransaction().add(R.id.ll_mine_loginAndRegister_content, mLoginFragment).commit();
    }

    public void onLoginAndRegister_Layout_LoginClicked(View view) {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
            Log.d("Fragment生命周期管理", "创建LoginFragment");
        }
        if (!mLoginFragment.isVisible()) {
            mLoginAndRegisterFragment.getChildFragmentManager().beginTransaction().replace(R.id.ll_mine_loginAndRegister_content, mLoginFragment).commit();
        }
    }

    public void onLoginAndRegister_Layout_RegisterClicked(View view) {
        if (mRegisterFragment == null) {
            mRegisterFragment = new RegisterFragment();
            Log.d("Fragment生命周期管理", "创建RegisterFragment");
        }
        if (!mRegisterFragment.isVisible()) {
            mLoginAndRegisterFragment.getChildFragmentManager().beginTransaction().replace(R.id.ll_mine_loginAndRegister_content, mRegisterFragment).commit();
        }
    }
}
