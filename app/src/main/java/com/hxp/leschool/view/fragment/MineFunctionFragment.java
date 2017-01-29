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
import com.hxp.leschool.databinding.MinefunctionFmBinding;
import com.hxp.leschool.viewmodel.MineFunctionViewModel;


/**
 * Created by hxp on 17-1-12.
 */


public class MineFunctionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MinefunctionFmBinding minefunctionFmBinding = DataBindingUtil.inflate(inflater, R.layout.minefunction_fm, container, false);
        new MineFunctionViewModel(this,minefunctionFmBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-MineFunction");
        return minefunctionFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理","onPause()触发-MineFunction");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理","onResume()触发-MineFunction");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理","onDestroy()触发-MineFunction");
    }
}

