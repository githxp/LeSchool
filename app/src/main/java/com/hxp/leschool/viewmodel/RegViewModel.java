package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.hxp.leschool.databinding.RegFmBinding;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.fragment.RegFragment;

import cn.bmob.v3.listener.SaveListener;


/**
 * Created by hxp on 17-1-20.
 */


public class RegViewModel {

    private RegFragment mRegFragment;
    private RegFmBinding mRegFmBinding;

    public RegViewModel(RegFragment regFragment, RegFmBinding regFmBinding) {

        mRegFragment = regFragment;
        mRegFmBinding = regFmBinding;

        mRegFmBinding.setMRegViewModel(this);
    }

    public void onReg_Layout_RegClicked(View view) {
        final String userName = mRegFmBinding.etRegUserName.getText().toString();
        final String userPassword = mRegFmBinding.etRegUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Toast.makeText(mRegFragment.getActivity(), "正在注册", Toast.LENGTH_SHORT).show();
            AVUser user = new AVUser();
            user.setUsername(userName);
            user.setPassword(userPassword);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        AVObject avObject = new AVObject("UserInfo");
                        avObject.put("userName", userName);
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
                        Toast.makeText(mRegFragment.getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mRegFragment.getActivity(), "注册失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mRegFragment.getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }
}
