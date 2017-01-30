package com.hxp.leschool.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.UploadingAdapter;
import com.hxp.leschool.databinding.UploadingFmBinding;
import com.hxp.leschool.databinding.UploadingItemBinding;
import com.hxp.leschool.model.operate.UploadingModelOpt;
import com.hxp.leschool.model.operate.UploadingModelOpt.UploadingOptCallback;
import com.hxp.leschool.view.fragment.UploadingFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class UploadingViewModel implements UploadingOptCallback {

    public UploadingModelOpt mUploadingModelOpt;
    private UploadingFragment mUploadingFragment;
    private UploadingFmBinding mUploadingFmBinding;
    private UploadingItemBinding mUploadingItemBinding;
    private UploadingAdapter mUploadingAdapter;

    public UploadingViewModel(UploadingFragment uploadingFragment, UploadingFmBinding uploadingFmBinding, UploadingItemBinding uploadingItemBinding) {

        mUploadingFragment = uploadingFragment;
        mUploadingFmBinding = uploadingFmBinding;
        mUploadingItemBinding = uploadingItemBinding;

        mUploadingFmBinding.rvUploadingContent.setLayoutManager(new LinearLayoutManager(mUploadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mUploadingFmBinding.rvUploadingContent.setAdapter(new RecyclerView.Adapter() {
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

        mUploadingAdapter = new UploadingAdapter(this);
        mUploadingModelOpt = new UploadingModelOpt(this, mUploadingFragment);

        mUploadingFmBinding.setMUploadingViewModel(this);
        mUploadingItemBinding.setMUploadingViewModel(this);

        mUploadingModelOpt.getData();
    }

    @Override
    public void uploadingGetdataSucceedCompleted() {
        mUploadingFmBinding.rvUploadingContent.setLayoutManager(new LinearLayoutManager(mUploadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mUploadingFmBinding.rvUploadingContent.setAdapter(mUploadingAdapter);
        Toast.makeText(mUploadingFragment.getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取成功回调接收方");
    }

    @Override
    public void uploadingGetdataFailedCompleted() {
        Toast.makeText(mUploadingFragment.getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取失败回调接收方");
    }

    @Override
    public void uploadingRefreshdataSucceedCompleted() {
        mUploadingAdapter.notifyDataSetChanged();
        Toast.makeText(mUploadingFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void uploadingRefreshdataFailedCompleted() {
        Toast.makeText(mUploadingFragment.getActivity(), "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新失败回调接收方");
    }

    @Override
    public void uploadingRefreshStateSucceedCompleted() {
        mUploadingAdapter.notifyDataSetChanged();
        Log.d("fragment", "DownloadModelOpt状态刷新失败回调接收方");
    }

    @Override
    public void uploadingRefreshStateFailedCompleted() {

    }
}
