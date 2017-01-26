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
import com.hxp.leschool.databinding.DownloadingFmBinding;
import com.hxp.leschool.databinding.DownloadingItemBinding;
import com.hxp.leschool.databinding.UploadingFmBinding;
import com.hxp.leschool.databinding.UploadingItemBinding;
import com.hxp.leschool.viewmodel.DownloadingViewModel;
import com.hxp.leschool.viewmodel.UploadingViewModel;

/**
 * Created by hxp on 17-1-25.
 */

public class UploadingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UploadingFmBinding uploadingFmBinding = DataBindingUtil.inflate(inflater, R.layout.uploading_fm, container, false);
        UploadingItemBinding uploadingItemBinding = DataBindingUtil.inflate(inflater, R.layout.uploading_item, container, false);
        new UploadingViewModel(this, uploadingFmBinding, uploadingItemBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-DownloadCompletedFragment");
        return uploadingFmBinding.getRoot();
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
