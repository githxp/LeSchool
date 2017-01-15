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
import com.hxp.leschool.databinding.ClassFmBinding;
import com.hxp.leschool.viewmodel.ClassViewModel;

/**
 * Created by hxp on 17-1-12.
 */

public class ClassFragment extends Fragment {

    private int i = 0;  //测试变量

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ClassFmBinding classFmBinding = DataBindingUtil.inflate(inflater, R.layout.class_fm, container, false);
        new ClassViewModel(this,classFmBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-Class");
        return classFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-Class");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-Class");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        i++;
        Log.d("Fragment生命周期管理", "onDestroy()触发-Class " + i);
    }
}
