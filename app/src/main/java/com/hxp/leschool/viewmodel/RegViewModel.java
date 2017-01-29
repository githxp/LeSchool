package com.hxp.leschool.viewmodel;

import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.RegFmBinding;
import com.hxp.leschool.model.bean.BmobUserModel;
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
        String userName = mRegFmBinding.etRegUserName.getText().toString();
        String userPassword = mRegFmBinding.etRegUserPassword.getText().toString();
        if ((!userName.equals("")) && (!userPassword.equals(""))) {
            Toast.makeText(mRegFragment.getActivity(), "正在注册", Toast.LENGTH_SHORT).show();
            BmobUserModel bmobUserModel = new BmobUserModel();
            bmobUserModel.setUsername(userName);
            bmobUserModel.setPassword(userPassword);
            bmobUserModel.setUserPicture(R.mipmap.ic_launcher);
            bmobUserModel.signUp(new SaveListener<BmobUserModel>() {
                @Override
                public void done(BmobUserModel bmobUserModel, BmobException e) {
                    if (e == null) {
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
