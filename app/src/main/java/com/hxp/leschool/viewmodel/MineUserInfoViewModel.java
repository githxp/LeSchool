package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.databinding.MineuserinfoFmBinding;
import com.hxp.leschool.model.operate.MineUserInfoModelOpt;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyApplication.LoginAndRegCallback;
import com.hxp.leschool.view.activity.LoginAndRegActivity;
import com.hxp.leschool.view.fragment.MineUserInfoFragment;



/**
 * Created by hxp on 17-1-13.
 */



public class MineUserInfoViewModel implements LoginAndRegCallback {

    public MineUserInfoModelOpt mMineUserInfoModelOpt;
    private MineUserInfoFragment mMineUserInfoFragment;
    private MineuserinfoFmBinding mMineuserinfoFmBinding;

    public MineUserInfoViewModel(MineUserInfoFragment mineUserInfoFragment, MineuserinfoFmBinding mineuserinfoFmBinding) {

        mMineUserInfoFragment = mineUserInfoFragment;
        mMineuserinfoFmBinding = mineuserinfoFmBinding;

        mMineUserInfoModelOpt = new MineUserInfoModelOpt();
        mMineUserInfoModelOpt.getData();
        Log.d("fragment", "用户信息数据opt获取数据");

        mMineuserinfoFmBinding.setMMineUserInfoViewModel(this);

        MyApplication.getInstance().setMineUserInfoViewModel(this);

        mMineuserinfoFmBinding.llMineUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mMineUserInfoFragment.getActivity(), LoginAndRegActivity.class);
                mMineUserInfoFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void loginSucceedCallback() {
        Log.d("fragment", "登陆成功回调接收方");
        Toast.makeText(mMineUserInfoFragment.getActivity(), "用户已登录", Toast.LENGTH_SHORT).show();
        mMineUserInfoModelOpt.getData();
    }

    @Override
    public void logoutSucceedCallback() {
        Log.d("fragment", "注销成功回调接收方");
        Toast.makeText(mMineUserInfoFragment.getActivity(), "用户已注销", Toast.LENGTH_SHORT).show();
        mMineUserInfoModelOpt.getData();
    }
}
