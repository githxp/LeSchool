package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.hxp.leschool.databinding.MineuserinfoFmBinding;
import com.hxp.leschool.model.opt.MineUserInfoModelOpt;
import com.hxp.leschool.view.activity.UserCenterActivity;
import com.hxp.leschool.view.fragment.MineUserInfoFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class MineUserInfoViewModel {

    public MineUserInfoModelOpt mMineUserInfoModelOpt;
    private MineUserInfoFragment mMineUserInfoFragment;
    private MineuserinfoFmBinding mMineuserinfoFmBinding;

    public MineUserInfoViewModel(final MineUserInfoFragment mineUserInfoFragment, MineuserinfoFmBinding mineuserinfoFmBinding) {

        mMineUserInfoFragment = mineUserInfoFragment;
        mMineuserinfoFmBinding = mineuserinfoFmBinding;

        mMineUserInfoModelOpt = new MineUserInfoModelOpt();
        mMineUserInfoModelOpt.get();
        Log.d("fragment", "用户信息数据opt获取数据");

        mMineuserinfoFmBinding.setMMineUserInfoViewModel(this);
    }

    public void ll_MineUserInfo_userCenterClicked(View view) {
        mMineUserInfoFragment.getActivity().startActivity(new Intent(mMineUserInfoFragment.getActivity(), UserCenterActivity.class));
    }
}
