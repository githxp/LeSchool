package com.hxp.leschool.viewmodel;

import com.hxp.leschool.databinding.LoginFmBinding;
import com.hxp.leschool.view.fragment.LoginFragment;

/**
 * Created by hxp on 17-1-20.
 */

public class LoginViewModel {

    private LoginFragment mLoginFragment;
    private LoginFmBinding mLoginFmBinding;

    public LoginViewModel(LoginFragment loginFragment, LoginFmBinding loginFmBinding) {

        mLoginFragment = loginFragment;
        mLoginFmBinding = loginFmBinding;

        mLoginFmBinding.setMLoginViewModel(this);
    }
}
