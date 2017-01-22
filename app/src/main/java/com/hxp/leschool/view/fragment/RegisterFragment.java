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
import com.hxp.leschool.databinding.RegisterFmBinding;
import com.hxp.leschool.viewmodel.LoginAndRegisterViewModel;
import com.hxp.leschool.viewmodel.RegisterViewModel;

/**
 * Created by hxp on 17-1-12.
 */

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RegisterFmBinding registerFmBinding = DataBindingUtil.inflate(inflater, R.layout.register_fm, container, false);
        new RegisterViewModel(this, registerFmBinding);
        return registerFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-Register");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-Register");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-Register");
    }
}
