package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.databinding.MineuserinfoFmBinding;
import com.hxp.leschool.model.operate.MineUserInfoModelOpt;
import com.hxp.leschool.view.fragment.LoginAndRegisterFragment;
import com.hxp.leschool.view.fragment.MineUserInfoFragment;

/**
 * Created by hxp on 17-1-13.
 */

public class MineUserInfoViewModel {

    public MineUserInfoModelOpt mMineUserInfoModelOpt;
    private MineUserInfoFragment mMineUserInfoFragment;
    private MineuserinfoFmBinding mMineuserinfoFmBinding;

    public MineUserInfoViewModel(MineUserInfoFragment mineUserInfoFragment, MineuserinfoFmBinding mineuserinfoFmBinding) {

        mMineUserInfoFragment = mineUserInfoFragment;
        mMineuserinfoFmBinding = mineuserinfoFmBinding;

        mMineUserInfoModelOpt = new MineUserInfoModelOpt();
        mMineUserInfoModelOpt.getData();
        Log.d("fragment", "用户信息数据opt获取数据");

        mineuserinfoFmBinding.setMMineUserInfoViewModel(this);

        mMineuserinfoFmBinding.llMineUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginAndRegisterFragment().show(mMineUserInfoFragment.getFragmentManager(), "loginAndRegister");
            }
        });
    }
}
