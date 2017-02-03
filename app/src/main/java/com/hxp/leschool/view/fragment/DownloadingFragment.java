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
import com.hxp.leschool.utils.event.DownloadEvent;
import com.hxp.leschool.viewmodel.DownloadingViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by hxp on 17-1-25.
 */


public class DownloadingFragment extends Fragment {

    private DownloadingViewModel mDownloadingViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DownloadingFmBinding downloadingFmBinding = DataBindingUtil.inflate(inflater, R.layout.downloading_fm, container, false);
        DownloadingItemBinding downloadingItemBinding = DataBindingUtil.inflate(inflater, R.layout.downloading_item, container, false);
        mDownloadingViewModel = new DownloadingViewModel(this, downloadingFmBinding, downloadingItemBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-DownloadCompletedFragment");
        return downloadingFmBinding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("Fragment", "onPause()触发-DownloadingFragment");
        Log.d("Fragment", "EventBus注册");
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        Log.d("Fragment", "onPause()触发-DownloadingFragment");
        Log.d("Fragment", "EventBus解除注册");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-DownloadingFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-DownloadingFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-DownloadCompletedFragment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStartDownload(DownloadEvent downloadEvent) {
        Log.d("fragment", "下载事件处理" + downloadEvent.getTitle() + " num:" + downloadEvent.getNum() + "process:" + downloadEvent.getProcess());
        //处理下载事件
        mDownloadingViewModel.handleDownloadEvent(downloadEvent);
    }
}
