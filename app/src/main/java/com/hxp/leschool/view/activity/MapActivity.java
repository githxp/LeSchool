package com.hxp.leschool.view.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.hxp.leschool.R;
import com.hxp.leschool.viewmodel.MapViewModel;

/**
 * Created by hxp on 17-1-12.
 */

public class MapActivity extends Activity {

    private TextureMapView mapView;
    private AMap mAMap;
    private MapViewModel mMapViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_at);
        Log.d("fragment", "创建了MapFActivity");
        mapView = (TextureMapView) findViewById(R.id.TeMap_map_map);
        mapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mapView.getMap();
            mAMap.moveCamera(CameraUpdateFactory.zoomTo(mAMap.getMaxZoomLevel()));
        }
        mMapViewModel = new MapViewModel(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d("Fragment", "onResume()触发-MapActivity");
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        Log.d("Fragment", "onPause()触发-MapActivity");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
        Log.d("Fragment", "onSaveInstanceState()触发-MapActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        Log.d("Fragment", "onDestroy()触发-MapActivity");
    }

    public AMap getAMap() {
        return mAMap;
    }
}
