package com.hxp.leschool.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.leschool.adapter.DownloadingAdapter;
import com.hxp.leschool.databinding.DownloadingFmBinding;
import com.hxp.leschool.databinding.DownloadingItemBinding;
import com.hxp.leschool.model.operate.DownloadingModelOpt.DownloadingOptCallback;
import com.hxp.leschool.model.operate.DownloadingModelOpt;
import com.hxp.leschool.view.fragment.DownloadingFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class DownloadingViewModel implements DownloadingOptCallback {

    public DownloadingModelOpt mDownloadingModelOpt;
    private DownloadingFragment mDownloadingFragment;
    private DownloadingFmBinding mDownloadingFmBinding;
    private DownloadingItemBinding mDownloadingItemBinding;
    private DownloadingAdapter mDownloadingAdapter;

    public DownloadingViewModel(DownloadingFragment downloadingFragment, DownloadingFmBinding downloadingFmBinding, DownloadingItemBinding downloadingItemBinding) {

        mDownloadingFragment = downloadingFragment;
        mDownloadingFmBinding = downloadingFmBinding;
        mDownloadingItemBinding = downloadingItemBinding;

        mDownloadingFmBinding.rvDownloadingContent.setLayoutManager(new LinearLayoutManager(mDownloadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mDownloadingFmBinding.rvDownloadingContent.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });

        mDownloadingAdapter = new DownloadingAdapter(this);
        mDownloadingModelOpt = new DownloadingModelOpt(this, mDownloadingFragment);

        mDownloadingFmBinding.setMDownloadingViewModel(this);
        mDownloadingItemBinding.setMDownloadingViewModel(this);

        mDownloadingModelOpt.getData();
    }

    @Override
    public void downloadingGetdataSucceedCompleted() {
        mDownloadingFmBinding.rvDownloadingContent.setLayoutManager(new LinearLayoutManager(mDownloadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mDownloadingFmBinding.rvDownloadingContent.setAdapter(mDownloadingAdapter);
        Log.d("fragment", "DownloadingModelOpt数据获取成功回调接收方");
    }

    @Override
    public void downloadingGetdataFailedCompleted() {
        Log.d("fragment", "DownloadingModelOpt数据获取失败回调接收方");
    }

    @Override
    public void downloadingRefreshdataSucceedCompleted() {
        mDownloadingAdapter.notifyDataSetChanged();
        Log.d("fragment", "DownloadingModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void downloadingRefreshdataFailedCompleted() {
        Log.d("fragment", "DownloadingModelOpt数据刷新失败回调接收方");
    }

    @Override
    public void downloadingRefreshStateSucceedCompleted() {
        mDownloadingAdapter.notifyDataSetChanged();
        Log.d("fragment", "DownloadingModelOpt状态刷新成功回调接收方");
    }

    @Override
    public void downloadingRefreshStateFailedCompleted() {
        Log.d("fragment", "DownloadingModelOpt状态刷新成功回调接收方");
    }


}
