package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hxp.leschool.R;
import com.hxp.leschool.databinding.MineuserinfoFmBinding;
import com.hxp.leschool.model.opt.MineUserInfoModelOpt;
import com.hxp.leschool.utils.AvatarHelper;
import com.hxp.leschool.view.activity.UserCenterActivity;
import com.hxp.leschool.view.fragment.MineUserInfoFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class MineUserInfoViewModel implements MineUserInfoModelOpt.MineUserInfoCallback {

    public MineUserInfoModelOpt mMineUserInfoModelOpt;
    private MineUserInfoFragment mMineUserInfoFragment;
    private MineuserinfoFmBinding mMineuserinfoFmBinding;

    public MineUserInfoViewModel(final MineUserInfoFragment mineUserInfoFragment, MineuserinfoFmBinding mineuserinfoFmBinding) {

        mMineUserInfoFragment = mineUserInfoFragment;
        mMineuserinfoFmBinding = mineuserinfoFmBinding;

        mMineUserInfoModelOpt = new MineUserInfoModelOpt(this);
        mMineUserInfoModelOpt.get();
        Log.d("fragment", "用户信息数据opt获取数据");

        mMineuserinfoFmBinding.setMMineUserInfoViewModel(this);
    }

    public void ll_MineUserInfo_userCenterClicked(View view) {
        mMineUserInfoFragment.getActivity().startActivity(new Intent(mMineUserInfoFragment.getActivity(), UserCenterActivity.class));
    }

    @Override
    public void get(String avatarUrl) {
        Log.d("fragment", "MineUserInfoOpt接收回调");
        //Glide.with(mMineUserInfoFragment.getContext()).load(avatarUrl).placeholder(R.drawable.ic_avatarloading).error(R.drawable.ic_avatarloadfail).diskCacheStrategy(DiskCacheStrategy.RESULT).into(mMineuserinfoFmBinding.imgMineUserInfoAvatar);
        new AvatarHelper(mMineUserInfoFragment.getContext(), mMineuserinfoFmBinding.imgMineUserInfoAvatar, avatarUrl);
    }
}
