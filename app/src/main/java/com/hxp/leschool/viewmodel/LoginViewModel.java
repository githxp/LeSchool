package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.hxp.leschool.databinding.LoginFmBinding;
import com.hxp.leschool.utils.MyApplication;
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

    public void onLogin_Layout_LoginClicked(View view) {
        String userName = mLoginFmBinding.etLoginUserName.getText().toString();
        String userPassword = mLoginFmBinding.etLoginUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Toast.makeText(mLoginFragment.getActivity(), "正在登陆", Toast.LENGTH_SHORT).show();
            AVUser user = new AVUser();
            user.logInInBackground(userName, userPassword, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {
                        Toast.makeText(mLoginFragment.getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                        Log.d("fragment", "登陆成功回调发送方");
                        MyApplication.getInstance().getLoginSucceedCallback().loginSucceedCallback();
                    } else {
                        Toast.makeText(mLoginFragment.getActivity(), "登陆失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mLoginFragment.getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLogin_Layout_LogoutClicked(View view) {
        if (!AVUser.getCurrentUser().getUsername().equals("")) {
            AVUser.getCurrentUser().logOut();
            MyApplication.getInstance().getLogoutSucceedCallback().logoutSucceedCallback();
        }
    }
}
