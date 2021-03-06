package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.SendaddreqAtBinding;
import com.hxp.leschool.viewmodel.SendAddReqViewModel;


/**
 * Created by hxp on 17-1-24.
 */


public class SendAddReqActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SendaddreqAtBinding sendaddreqAtBinding = DataBindingUtil.setContentView(this, R.layout.sendaddreq_at);
        Log.d("fragment", "创建了SendAddReqActivityy");
        new SendAddReqViewModel(this, sendaddreqAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-SendAddReqActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-SendAddReqActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-SendAddReqActivity");
    }
}

