package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.RegFmBinding;
import com.hxp.leschool.model.server.object.MyUserObject;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.fragment.RegFragment;

import cn.bmob.v3.exception.BmobException;
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
        String userPassword = mRegFmBinding.etRegUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Toast.makeText(mRegFragment.getActivity(), "正在注册", Toast.LENGTH_SHORT).show();
            final MyUser myUser = new MyUser();
            myUser.setUsername(userName);
            myUser.setPassword(userPassword);
            myUser.setAvatar("默认头像");
            myUser.signUp(MyApplication.getInstance(),new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(mRegFragment.getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    MyUserObject myUserObject = new MyUserObject();
                    myUserObject.setUserName(myUser.getUsername());
                    myUserObject.setAvatar(myUser.getAvatar());
                    myUserObject.setUserID(myUser.getObjectId());
                    myUserObject.save(MyApplication.getInstance(),new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Log.d("fragment", "用户对象创建成功：");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.d("fragment", "用户对象创建失败：" + s);
                        }
                    });
                }
                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(mRegFragment.getActivity(), "注册失败" + s, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(mRegFragment.getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }
}
