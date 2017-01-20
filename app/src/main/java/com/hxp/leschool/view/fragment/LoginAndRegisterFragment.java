package com.hxp.leschool.view.fragment;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginandregisterFmBinding;
import com.hxp.leschool.viewmodel.LoginAndRegisterViewModel;

/**
 * Created by hxp on 17-1-19.
 */

public class LoginAndRegisterFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginandregisterFmBinding loginandregisterFmBinding = DataBindingUtil.inflate(inflater, R.layout.loginandregister_fm, container, false);
        new LoginAndRegisterViewModel(this,loginandregisterFmBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-LoginAndRegister");
        return loginandregisterFmBinding.getRoot();
    }
}
