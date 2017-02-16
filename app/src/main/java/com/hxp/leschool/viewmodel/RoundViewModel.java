package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.hxp.leschool.R;
import com.hxp.leschool.view.activity.NavActivity;
import com.hxp.leschool.view.activity.RoundActivity;
import com.hxp.leschool.widget.SubNavbar;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hxp on 17-1-24.
 */


public class RoundViewModel implements LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener {

    public AMapLocationClient mAMapLocationClient;
    private AMap mAMap;
    private RoundActivity mRoundActivity;
    private OnLocationChangedListener mOnLocationChangedListener;
    private AMapLocationClientOption mAMapLocationClientOption;
    private ArrayList<MarkerOptions> mMarkerOptionData = new ArrayList<>();
    private PoiSearch.Query mPoiSearchQuery;
    private PoiSearch mPoiSearch;
    private AMapLocation mAMapLocation;
    private int mCurrentPageNum = 0;
    private int mCurrentPageSize = 7;
    private ArrayList<PoiItem> mPoiItem;
    private SubNavbar mSubNavbar;


    public RoundViewModel(RoundActivity roundActivity) {
        mRoundActivity = roundActivity;
        mAMap = mRoundActivity.getAMap();

        mSubNavbar = (SubNavbar) mRoundActivity.findViewById(R.id.subNavbar_Round_content);
        mSubNavbar.setTitle("附近");

        mAMap.setLocationSource(this);
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        mAMap.setMyLocationEnabled(true);
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        mAMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(mRoundActivity, "您点击了" + marker.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        mAMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {

            private View view;

            @Override
            public View getInfoWindow(final Marker marker) {
                if (view == null) {
                    view = LayoutInflater.from(mRoundActivity).inflate(R.layout.myinfowindow_item, null);
                }
                ((TextView) view.findViewById(R.id.tv_Round_title)).setText(marker.getTitle());
                ((TextView) view.findViewById(R.id.tv_Round_snippet)).setText(marker.getSnippet());
                view.findViewById(R.id.ll_Round_nav).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mRoundActivity, NavActivity.class);
                        intent.putExtra("fromLat", mAMapLocation.getLatitude());
                        intent.putExtra("fromLon", mAMapLocation.getLongitude());
                        intent.putExtra("toLat", marker.getPosition().latitude);
                        intent.putExtra("toLon", marker.getPosition().longitude);
                        mRoundActivity.startActivity(intent);
                    }
                });
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        mRoundActivity.findViewById(R.id.btn_Round_switchResultPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentPageNum++;
                poiSearch();
            }
        });
    }

    private void poiSearch() {
        mPoiSearchQuery = new PoiSearch.Query("", "餐饮服务|生活服务|购物服务|医疗保健服务|住宿服务|交通设施服务");
        mPoiSearchQuery.setPageSize(mCurrentPageSize);
        mPoiSearchQuery.setPageNum(mCurrentPageNum);
        mPoiSearch = new PoiSearch(mRoundActivity, mPoiSearchQuery);
        mPoiSearch.setOnPoiSearchListener(this);
        mPoiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(mAMapLocation.getLatitude(), mAMapLocation.getLongitude()), 200));
        mPoiSearch.searchPOIAsyn();
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mOnLocationChangedListener = onLocationChangedListener;
        if (mAMapLocationClient == null) {
            mAMapLocationClient = new AMapLocationClient(mRoundActivity);
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
                if (mAMapLocation == null) {
                    mAMapLocation = aMapLocation;
                    poiSearch();
                    Log.d("fragment", "poi");
                }
                Log.d("fragment", "定位成功");
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.d("fragment", errText);
            }
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == AMapException.CODE_AMAP_SUCCESS) {
            if (poiResult != null && poiResult.getQuery() != null) {
                if (poiResult.getQuery().equals(mPoiSearchQuery)) {
                    mPoiItem = poiResult.getPois();
                    Log.d("fragment", "找到：" + mPoiItem.size());
                    if (mPoiItem != null && mPoiItem.size() > 0) {
                        mAMap.clear();
                        mMarkerOptionData.clear();
                        for (int j = 0; j < mPoiItem.size(); j++) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(new LatLng(mPoiItem.get(j).getLatLonPoint().getLatitude(),
                                    mPoiItem.get(j).getLatLonPoint().getLongitude()))
                                    .title(mPoiItem.get(j).getTitle());
                            mMarkerOptionData.add(markerOptions);
                        }
                        mAMap.addMarkers(mMarkerOptionData, true);
                    } else {
                        Toast.makeText(mRoundActivity, "返回第一页", Toast.LENGTH_SHORT).show();
                        mCurrentPageNum = 0;
                        poiSearch();
                    }
                }
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }
}