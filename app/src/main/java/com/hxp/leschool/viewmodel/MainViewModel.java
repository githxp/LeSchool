package com.hxp.leschool.viewmodel;

import com.hxp.leschool.databinding.MainBinding;
import com.hxp.leschool.view.activity.MainActivity;

/**
 * Created by hxp on 17-1-12.
 */

public class MainViewModel {

    private MainBinding mMainBinding;

    public MainViewModel(MainActivity mainActivity, MainBinding mainBinding) {
        MainActivity mMainActivity = mainActivity;
        mMainBinding = mainBinding;
        mMainActivity.setSupportActionBar(mMainBinding.tbMainToolbar);
        //mMainBinding.setMMainViewModel(this);
    }
}
