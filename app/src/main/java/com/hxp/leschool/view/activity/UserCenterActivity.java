package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.UsercenterAtBinding;
import com.hxp.leschool.viewmodel.UserCenterViewModel;


/**
 * Created by hxp on 17-1-22.
 */


public class UserCenterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UsercenterAtBinding usercenterAtBinding = DataBindingUtil.setContentView(this, R.layout.usercenter_at);
        Log.d("fragment", "创建了UserCenterActivity");
        new UserCenterViewModel(this, usercenterAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-UserCenterActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-UserCenterActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-UserCenterActivity");
    }
}
