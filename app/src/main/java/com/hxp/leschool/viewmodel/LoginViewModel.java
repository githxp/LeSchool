package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.hxp.leschool.databinding.LoginFmBinding;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.event.LoginSwitchRegEvent;
import com.hxp.leschool.view.activity.MainActivity;
import com.hxp.leschool.view.fragment.LoginFragment;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


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

    public void btn_Login_login(View view) {
        String userName = mLoginFmBinding.etLoginUserName.getText().toString();
        String userPassword = mLoginFmBinding.etLoginUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Log.d("fragment:", "username" + userName + " password:" + userPassword);
            AVUser user = new AVUser();
            user.logInInBackground(userName, userPassword, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {
                        Toast.makeText(mLoginFragment.getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                        MyApplication.getInstance().connectToServer();
                        Log.d("fragment", "登陆成功回调发送方");
                        Intent intent = new Intent(mLoginFragment.getActivity(), MainActivity.class);
                        mLoginFragment.getActivity().startActivity(intent);
                        mLoginFragment.getActivity().finish();
                    } else {
                        Toast.makeText(mLoginFragment.getActivity(), "登陆失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mLoginFragment.getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }

    }

    public void ll_Login_reg(View view) {
        EventBus.getDefault().post(new LoginSwitchRegEvent());
    }
}
