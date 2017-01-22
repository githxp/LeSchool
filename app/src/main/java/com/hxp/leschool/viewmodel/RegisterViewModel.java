package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.hxp.leschool.databinding.RegisterFmBinding;
import com.hxp.leschool.view.fragment.RegisterFragment;

/**
 * Created by hxp on 17-1-20.
 */

public class RegisterViewModel {

    private RegisterFragment mRegisterFragment;
    private RegisterFmBinding mRegisterFmBinding;

    public RegisterViewModel(RegisterFragment registerFragment, RegisterFmBinding registerFmBinding) {

        mRegisterFragment = registerFragment;
        mRegisterFmBinding = registerFmBinding;

        mRegisterFmBinding.setMRegisterViewModel(this);
    }

    public void onRegister_Layout_RegisterClicked(View view) {
        final String userName = mRegisterFmBinding.etRegisterUserName.getText().toString();
        final String userPassword = mRegisterFmBinding.etRegisterUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Toast.makeText(mRegisterFragment.getActivity(), "正在注册", Toast.LENGTH_SHORT).show();
            AVUser user = new AVUser();
            user.setUsername(userName);
            user.setPassword(userPassword);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        AVObject avObject = new AVObject("UserInfo");
                        avObject.put("useNname", userName);
                        avObject.put("userPassword", userPassword);
                        avObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    Log.d("fragment", "UserInfo创建对象完成");
                                } else {
                                    Log.d("fragment", "UserInfo创建对象失败" + e.getMessage());
                                }
                            }
                        });
                        Toast.makeText(mRegisterFragment.getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mRegisterFragment.getActivity(), "注册失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mRegisterFragment.getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }
}
