package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.DownloadAtBinding;
import com.hxp.leschool.viewmodel.DownloadViewModel;


/**
 * Created by hxp on 17-1-24.
 */


public class DownloadActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DownloadAtBinding downloadAtBinding = DataBindingUtil.setContentView(this, R.layout.download_at);
        Log.d("fragment", "创建了DownloadActivity");
        new DownloadViewModel(this, downloadAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-DownloadActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-DownloadActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-DownloadActivity");
    }
}

