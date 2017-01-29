package com.hxp.leschool.view.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.MineuserinfoFmBinding;
import com.hxp.leschool.viewmodel.MineUserInfoViewModel;


/**
 * Created by hxp on 17-1-12.
 */


public class MineUserInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MineuserinfoFmBinding mineuserinfoFmBinding = DataBindingUtil.inflate(inflater, R.layout.mineuserinfo_fm, container, false);
        new MineUserInfoViewModel(this, mineuserinfoFmBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-MineUserInfoFragment");
        return mineuserinfoFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-MineUserInfoFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-MineUserInfoFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-MineUserInfoFragment");
    }
}

