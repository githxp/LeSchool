package com.hxp.leschool.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.DownloadingAdapter;
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
        mUploadingFmBinding.swifreshUploadingContent.setRefreshing(true);

        mUploadingAdapter = new UploadingAdapter(this);
        mUploadingModelOpt = new UploadingModelOpt(this, mUploadingFragment);

        mUploadingFmBinding.setMUploadingViewModel(this);
        mUploadingItemBinding.setMUploadingViewModel(this);

        mUploadingModelOpt.getData();

        mUploadingFmBinding.swifreshUploadingContent.setProgressViewOffset(true, 0, 50);
        mUploadingFmBinding.swifreshUploadingContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mUploadingFmBinding.swifreshUploadingContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mUploadingModelOpt.refreshData();
                    }
                }
        );

        mUploadingAdapter.setOnItemClickListener(new UploadingAdapter.OnItemClickListener() {
            @Override
            public void onItemPauseUploadClick(View view, int position) {
                //暂停上传
                mUploadingModelOpt.pauseUpload(position);
            }

            @Override
            public void onItemDelUploadClick(View view, int position) {
                //删除上传
                mUploadingModelOpt.delUpload(position);
            }
        });
    }

    @Override
    public void uploadingGetdataSucceedCompleted() {
        mUploadingFmBinding.rvUploadingContent.setLayoutManager(new LinearLayoutManager(mUploadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mUploadingFmBinding.rvUploadingContent.setAdapter(mUploadingAdapter);
        mUploadingFmBinding.swifreshUploadingContent.setRefreshing(false);
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
        mUploadingFmBinding.swifreshUploadingContent.setRefreshing(false);
        mUploadingAdapter.notifyDataSetChanged();
        Toast.makeText(mUploadingFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void uploadingRefreshdataFailedCompleted() {
        Toast.makeText(mUploadingFragment.getActivity(), "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新失败回调接收方");
    }
}
