package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginFmBinding;
import com.hxp.leschool.model.bean.BmobUserModel;
import com.hxp.leschool.view.fragment.LoginFragment;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
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

    public void onLogin_Layout_LoginClicked(View view) {
        String userName = mLoginFmBinding.etLoginUserName.getText().toString();
        String userPassword = mLoginFmBinding.etLoginUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Log.d("fragment:", "username" + userName + " password:" + userPassword);
            BmobUserModel bmobUserModel = new BmobUserModel();
            bmobUserModel.setUsername(userName);
            bmobUserModel.setPassword(userPassword);
            bmobUserModel.setUserPicture(R.mipmap.ic_launcher);
            bmobUserModel.login(new SaveListener<BmobUserModel>() {
                @Override
                public void done(BmobUserModel bmobUserModel, BmobException e) {
                    if (e == null) {
                        Toast.makeText(mLoginFragment.getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
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
        if (BmobUser.getCurrentUser() != null) {
            BmobUser.logOut();
            Toast.makeText(mLoginFragment.getActivity(), "注销成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mLoginFragment.getActivity(), "暂时无用户登陆", Toast.LENGTH_SHORT).show();
        }

    }
}
