package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.navi.AMapNaviView;
import com.hxp.leschool.R;
import com.hxp.leschool.viewmodel.NavViewModel;


/**
 * 导航
 * Created by hxp on 17-1-22.
 */


public class NavActivity extends Activity {

    private AMapNaviView mAMapNaviView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_at);
        Log.d("fragment", "创建了NavActivity");
        mAMapNaviView = (AMapNaviView) findViewById(R.id.nav_Nav_content);
        mAMapNaviView.onCreate(savedInstanceState);
        new NavViewModel(this);
    }

    public AMapNaviView getAMapNaviView(){
        return mAMapNaviView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mAMapNaviView.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-NavActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        mAMapNaviView.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-NavActivity");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Fragment", "onSaveInstanceState()触发-NavActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-NavActivity");
    }
}
