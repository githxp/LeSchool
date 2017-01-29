package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.LoginandregAtBinding;
import com.hxp.leschool.viewmodel.LoginAndRegViewModel;


/**
 * Created by hxp on 17-1-19.
 */


public class LoginAndRegActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginandregAtBinding loginandregAtBinding = DataBindingUtil.setContentView(this, R.layout.loginandreg_at);
        Log.d("fragment", "创建了LoginAndRegActivity");
        new LoginAndRegViewModel(this, loginandregAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-LoginAndRegActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-LoginAndRegActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-LoginAndRegActivity");
    }
}
