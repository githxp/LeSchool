package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginFmBinding;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.MyApplication;
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
            MyUser myUser = new MyUser();
            myUser.setUsername(userName);
            myUser.setPassword(userPassword);
            //myUser.setAvatar(R.mipmap.ic_launcher);
            myUser.login(MyApplication.getInstance(), new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(mLoginFragment.getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                    MyApplication.getInstance().getLoginAndRegCallback().loginSucceedCallback();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(mLoginFragment.getActivity(), "登陆失败" + s, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(mLoginFragment.getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLogin_Layout_LogoutClicked(View view) {
        if (BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class) != null) {
            BmobUser.logOut(MyApplication.getInstance());
            Toast.makeText(mLoginFragment.getActivity(), "注销成功", Toast.LENGTH_SHORT).show();
            MyApplication.getInstance().getLoginAndRegCallback().logoutSucceedCallback();
        } else {
            Toast.makeText(mLoginFragment.getActivity(), "暂时无用户登陆", Toast.LENGTH_SHORT).show();
        }

    }
}
