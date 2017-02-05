package com.hxp.leschool.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.hxp.leschool.adapter.DownloadingAdapter;
import com.hxp.leschool.databinding.DownloadingFmBinding;
import com.hxp.leschool.databinding.DownloadingItemBinding;
import com.hxp.leschool.model.opt.DownloadingModelOpt;
import com.hxp.leschool.model.opt.DownloadingModelOpt.DownloadCallback;
import com.hxp.leschool.utils.event.DownloadEvent;
import com.hxp.leschool.view.fragment.DownloadingFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class DownloadingViewModel implements DownloadCallback {

    public DownloadingModelOpt mDownloadingModelOpt;
    private DownloadingFragment mDownloadingFragment;
    private DownloadingFmBinding mDownloadingFmBinding;
    private DownloadingItemBinding mDownloadingItemBinding;
    private DownloadingAdapter mDownloadingAdapter;

    public DownloadingViewModel(DownloadingFragment downloadingFragment, DownloadingFmBinding downloadingFmBinding, DownloadingItemBinding downloadingItemBinding) {

        mDownloadingFragment = downloadingFragment;
        mDownloadingFmBinding = downloadingFmBinding;
        mDownloadingItemBinding = downloadingItemBinding;

        mDownloadingAdapter = new DownloadingAdapter(this);
        mDownloadingModelOpt = new DownloadingModelOpt(this);

        mDownloadingFmBinding.rvDownloadingContent.setLayoutManager(new LinearLayoutManager(mDownloadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mDownloadingFmBinding.rvDownloadingContent.setAdapter(mDownloadingAdapter);

        mDownloadingFmBinding.setMDownloadingViewModel(this);
        mDownloadingItemBinding.setMDownloadingViewModel(this);
    }

    @Override
    public void refresh() {
        mDownloadingAdapter.notifyDataSetChanged();
        Log.d("fragment", "DownloadingModelOpt数据刷新成功回调接收方");
    }

    //处理下载事件
    public void handleDownloadEvent(DownloadEvent downloadEvent){
        Log.d("fragment","交给downloadingopt处理");
        mDownloadingModelOpt.handleDownloadEvent(downloadEvent);
    }
}
