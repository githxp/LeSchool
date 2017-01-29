package com.hxp.leschool.view.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.DownloadcompletedFmBinding;
import com.hxp.leschool.databinding.DownloadcompletedItemBinding;
import com.hxp.leschool.viewmodel.DownloadCompletedViewModel;


/**
 * Created by hxp on 17-1-25.
 */


public class DownloadCompletedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DownloadcompletedFmBinding downloadcompletedFmBinding = DataBindingUtil.inflate(inflater, R.layout.downloadcompleted_fm, container, false);
        DownloadcompletedItemBinding downloadcompletedItemBinding = DataBindingUtil.inflate(inflater, R.layout.downloadcompleted_item, container, false);
        new DownloadCompletedViewModel(this, downloadcompletedFmBinding,downloadcompletedItemBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-DownloadCompletedFragment");
        return downloadcompletedFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-DownloadCompletedFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-DownloadCompletedFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-DownloadCompletedFragment");
    }
}
