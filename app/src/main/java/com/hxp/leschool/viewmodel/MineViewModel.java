package com.hxp.leschool.viewmodel;

import android.util.Log;

import com.hxp.leschool.databinding.MineFmBinding;
import com.hxp.leschool.view.fragment.MineFragment;
import com.hxp.leschool.view.fragment.MineFunctionFragment;
import com.hxp.leschool.view.fragment.MineUserInfoFragment;

/**
 * Created by hxp on 17-1-13.
 */

public class MineViewModel {

    private MineFragment mMineFragment;
    private MineFmBinding mMineFmBinding;
    private MineUserInfoFragment mUserInfomationFragment;
    private MineFunctionFragment mMineFunctionFragment;

    public MineViewModel(MineFragment mineFragment, MineFmBinding mineFmBinding) {

        mMineFmBinding = mineFmBinding;
        mMineFragment = mineFragment;

        if (mUserInfomationFragment == null) {
            mUserInfomationFragment = new MineUserInfoFragment();
            Log.d("fragment", "创建UserInfomationFragment");
        }
        if (mMineFunctionFragment == null) {
            mMineFunctionFragment = new MineFunctionFragment();
            Log.d("fragment", "创建MineFunctionFragment");
        }

        mineFmBinding.setMMineViewModel(this);

        mMineFragment.getActivity().getFragmentManager().beginTransaction().add(mMineFmBinding.llMineUserInfomation.getId(), mUserInfomationFragment).commit();
        Log.d("fragment", "UserInfomationFragment已添加到视图");
        mMineFragment.getActivity().getFragmentManager().beginTransaction().add(mMineFmBinding.llMineFunction.getId(), mMineFunctionFragment).commit();
        Log.d("fragment", "MineFunctionFragment已添加到视图");
    }
}
