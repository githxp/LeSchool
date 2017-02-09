package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.ScoreAtBinding;
import com.hxp.leschool.viewmodel.ScoreViewModel;


/**
 * Created by hxp on 17-1-22.
 */


public class ScoreActivity extends Activity {

    private ScoreAtBinding scoreAtBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoreAtBinding = DataBindingUtil.setContentView(this, R.layout.score_at);
        Log.d("fragment", "创建了ScoreActivity");
        new ScoreViewModel(this, scoreAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-ScoreActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-ScoreActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-ScoreActivity");
    }
}
