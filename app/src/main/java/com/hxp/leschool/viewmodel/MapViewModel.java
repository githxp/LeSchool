package com.hxp.leschool.viewmodel;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.hxp.leschool.view.activity.MapActivity;


/**
 * Created by hxp on 17-1-24.
 */


public class MapViewModel implements LocationSource, AMapLocationListener {

    public AMapLocationClient mAMapLocationClient;
    private AMap mAMap;
    private MapActivity mMapActivity;
    private OnLocationChangedListener mOnLocationChangedListener;
    private AMapLocationClientOption mAMapLocationClientOption;

    public MapViewModel(MapActivity mapFActivity) {
        mMapActivity = mapFActivity;
        mAMap = mMapActivity.getAMap();

        mAMap.setLocationSource(this);
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        mAMap.setMyLocationEnabled(true);
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mOnLocationChangedListener = onLocationChangedListener;
        if (mAMapLocationClient == null) {
            mAMapLocationClient = new AMapLocationClient(mMapActivity);
            mAMapLocationClientOption = new AMapLocationClientOption();
            mAMapLocationClient.setLocationListener(this);
            mAMapLocationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
            mAMapLocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mOnLocationChangedListener = null;
        if (mAMapLocationClient != null) {
            mAMapLocationClient.stopLocation();
            mAMapLocationClient.onDestroy();
        }
        mAMapLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mOnLocationChangedListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mOnLocationChangedListener.onLocationChanged(aMapLocation);
                Log.d("fragment", "定位成功");
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.d("fragment", errText);
            }
        }
    }
}
