package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.ClassdetailAtBinding;
import com.hxp.leschool.viewmodel.ClassDetailViewModel;

/**
 * Created by hxp on 17-1-25.
 */

public class ClassDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClassdetailAtBinding classdetailAtBinding = DataBindingUtil.setContentView(this, R.layout.classdetail_at);
        Log.d("fragment", "创建了ClassDetailActivity");
        new ClassDetailViewModel(this, classdetailAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-ClassDetailActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-ClassDetailActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-ClassDetailActivity");
    }
}
