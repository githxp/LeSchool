package com.hxp.leschool.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.DownloadCompletedAdapter;
import com.hxp.leschool.databinding.DownloadcompletedFmBinding;
import com.hxp.leschool.databinding.DownloadcompletedItemBinding;
import com.hxp.leschool.model.opt.DownloadCompletedModelOpt;
import com.hxp.leschool.model.opt.DownloadCompletedModelOpt.DownloadCompletedOptCallback;
import com.hxp.leschool.view.fragment.DownloadCompletedFragment;



/**
 * Created by hxp on 17-1-13.
 */


public class DownloadCompletedViewModel implements DownloadCompletedOptCallback {

    public DownloadCompletedModelOpt mDownloadCompletedModelOpt;
    private DownloadCompletedFragment mDownloadCompletedFragment;
    private DownloadcompletedItemBinding mDownloadcompletedItemBinding;
    private DownloadcompletedFmBinding mDownloadcompletedFmBinding;
    private DownloadCompletedAdapter mDownloadCompletedAdapter;

    public DownloadCompletedViewModel(DownloadCompletedFragment downloadCompletedFragment, DownloadcompletedFmBinding downloadcompletedFmBinding, DownloadcompletedItemBinding downloadcompletedItemBinding) {

        mDownloadCompletedFragment = downloadCompletedFragment;
        mDownloadcompletedFmBinding = downloadcompletedFmBinding;
        mDownloadcompletedItemBinding = downloadcompletedItemBinding;

        mDownloadcompletedFmBinding.rvDownloadCompletedContent.setLayoutManager(new LinearLayoutManager(mDownloadCompletedFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mDownloadcompletedFmBinding.rvDownloadCompletedContent.setAdapter(new RecyclerView.Adapter() {
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
        mDownloadcompletedFmBinding.swifreshDownloadCompletedContent.setRefreshing(true);

        mDownloadCompletedAdapter = new DownloadCompletedAdapter(this);
        mDownloadCompletedModelOpt = new DownloadCompletedModelOpt(this, mDownloadCompletedFragment);

        mDownloadcompletedFmBinding.setMDownloadCompletedViewModel(this);
        mDownloadcompletedItemBinding.setMDownloadCompletedViewModel(this);

        mDownloadCompletedModelOpt.getData();

        mDownloadcompletedFmBinding.swifreshDownloadCompletedContent.setProgressViewOffset(true, 0, 50);
        mDownloadcompletedFmBinding.swifreshDownloadCompletedContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mDownloadcompletedFmBinding.swifreshDownloadCompletedContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mDownloadCompletedModelOpt.refreshData();
                    }
                }
        );

        mDownloadCompletedAdapter.setOnItemClickListener(new DownloadCompletedAdapter.OnItemClickListener() {
            @Override
            public void onItemOpenFileClick(View view, int position) {
                //打开文件
                mDownloadCompletedModelOpt.openFile(position);
            }

            @Override
            public void onItemDelFileClick(View view, int position) {
                //删除文件
                mDownloadCompletedModelOpt.delFile(position);
            }
        });
    }

    @Override
    public void downloadGetdataSucceedCompleted() {
        mDownloadcompletedFmBinding.rvDownloadCompletedContent.setLayoutManager(new LinearLayoutManager(mDownloadCompletedFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mDownloadcompletedFmBinding.rvDownloadCompletedContent.setAdapter(mDownloadCompletedAdapter);
        mDownloadcompletedFmBinding.swifreshDownloadCompletedContent.setRefreshing(false);
        Toast.makeText(mDownloadCompletedFragment.getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取成功回调接收方");
    }

    @Override
    public void downloadGetdataFailedCompleted() {
        Toast.makeText(mDownloadCompletedFragment.getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取失败回调接收方");
    }

    @Override
    public void downloadRefreshdataSucceedCompleted() {
        mDownloadcompletedFmBinding.swifreshDownloadCompletedContent.setRefreshing(false);
        mDownloadCompletedAdapter.notifyDataSetChanged();
        Toast.makeText(mDownloadCompletedFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void downloadRefreshdataFailedCompleted() {
        Toast.makeText(mDownloadCompletedFragment.getActivity(), "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新失败回调接收方");
    }
}
