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
import com.hxp.leschool.databinding.RegFmBinding;
import com.hxp.leschool.viewmodel.RegViewModel;


/**
 * Created by hxp on 17-1-12.
 */


public class RegFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RegFmBinding regFmBinding = DataBindingUtil.inflate(inflater, R.layout.reg_fm, container, false);
        new RegViewModel(this, regFmBinding);
        return regFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment", "onPause()触发-Register");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume()触发-Register");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment", "onDestroy()触发-Register");
    }
}
