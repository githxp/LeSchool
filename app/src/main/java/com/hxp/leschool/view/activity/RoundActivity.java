package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.hxp.leschool.R;
import com.hxp.leschool.viewmodel.RoundViewModel;


/**
 * 附近
 * Created by hxp on 17-1-22.
 */


public class RoundActivity extends Activity {

    private TextureMapView mapView;
    private AMap mAMap;
    private RoundViewModel mRoundViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_at);
        Log.d("fragment", "创建了RoundActivity");
        mapView = (TextureMapView) findViewById(R.id.TeMap_Round_map);
        mapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mapView.getMap();
            mAMap.moveCamera(CameraUpdateFactory.zoomTo(mAMap.getMaxZoomLevel()));
        }
        mRoundViewModel = new RoundViewModel(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-RoundActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-RoundActivity");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
        Log.d("Fragment", "onSaveInstanceState()触发-RoundActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mRoundViewModel.mAMapLocationClient) {
            mRoundViewModel.mAMapLocationClient.onDestroy();
        }
        Log.d("Fragment生命周期管理", "onDestroy()触发-RoundActivity");
    }

    public AMap getAMap() {
        return mAMap;
    }
}
