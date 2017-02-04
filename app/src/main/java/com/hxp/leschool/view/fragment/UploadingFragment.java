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
import com.hxp.leschool.databinding.UploadingFmBinding;
import com.hxp.leschool.databinding.UploadingItemBinding;
import com.hxp.leschool.utils.event.DownloadEvent;
import com.hxp.leschool.utils.event.UploadEvent;
import com.hxp.leschool.viewmodel.UploadingViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by hxp on 17-1-25.
 */


public class UploadingFragment extends Fragment {

    private UploadingViewModel mUploadingViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UploadingFmBinding uploadingFmBinding = DataBindingUtil.inflate(inflater, R.layout.uploading_fm, container, false);
        UploadingItemBinding uploadingItemBinding = DataBindingUtil.inflate(inflater, R.layout.uploading_item, container, false);
        mUploadingViewModel = new UploadingViewModel(this, uploadingFmBinding, uploadingItemBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-UploadingFragment");
        return uploadingFmBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("Fragment", "onPause()触发-UploadingFragment");
        Log.d("Fragment", "EventBus注册");
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        Log.d("Fragment", "onPause()触发-UploadingFragment");
        Log.d("Fragment", "EventBus解除注册");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-UploadingFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-UploadingFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-UploadingFragment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleUploadEvent(UploadEvent uploadEvent) {
        Log.d("fragment", "上传事件处理" + uploadEvent.getTitle() + "process:" + uploadEvent.getProcess());
        //处理上传事件
        mUploadingViewModel.handleUploadEvent(uploadEvent);
    }
}
