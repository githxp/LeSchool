package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.DownloadAdapter;
import com.hxp.leschool.databinding.DownloadAtBinding;
import com.hxp.leschool.model.operate.DownloadModelOpt;
import com.hxp.leschool.model.operate.DownloadModelOpt.DownloadOptCallback;
import com.hxp.leschool.view.activity.DownloadActivity;


/**
 * Created by hxp on 17-1-13.
 */

public class DownloadViewModel implements DownloadOptCallback {

    public DownloadModelOpt mDownloadModelOpt;
    private DownloadActivity mDownloadActivity;
    private DownloadAtBinding mDownloadAtBinding;
    private DownloadAdapter mDownloadAdapter;

    public DownloadViewModel(DownloadActivity downloadActivity, DownloadAtBinding downloadAtBinding) {

        mDownloadActivity = downloadActivity;
        mDownloadAtBinding = downloadAtBinding;

        mDownloadAtBinding.rvDownloadContent.setLayoutManager(new LinearLayoutManager(mDownloadActivity, LinearLayoutManager.VERTICAL, false));
        mDownloadAtBinding.rvDownloadContent.setAdapter(new RecyclerView.Adapter() {
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
        mDownloadAtBinding.swifreshDownloadContent.setRefreshing(true);

        mDownloadAdapter = new DownloadAdapter(this);
        mDownloadModelOpt = new DownloadModelOpt(this);

        mDownloadAtBinding.setMDownloadViewModel(this);

        mDownloadModelOpt.getData();

        mDownloadAtBinding.swifreshDownloadContent.setProgressViewOffset(true, 0, 50);
        mDownloadAtBinding.swifreshDownloadContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mDownloadAtBinding.swifreshDownloadContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mDownloadModelOpt.refreshData();
                    }
                }
        );

        mDownloadAdapter.setOnItemClickListener(new DownloadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mDownloadActivity, "click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void downloadGetdataSucceedCompleted() {
        mDownloadAtBinding.rvDownloadContent.setLayoutManager(new LinearLayoutManager(mDownloadActivity, LinearLayoutManager.VERTICAL, false));
        mDownloadAtBinding.rvDownloadContent.setAdapter(mDownloadAdapter);
        mDownloadAtBinding.swifreshDownloadContent.setRefreshing(false);
        Toast.makeText(mDownloadActivity, "获取数据成功", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取成功回调接收方");
    }

    @Override
    public void downloadGetdataFailedCompleted() {
        Toast.makeText(mDownloadActivity, "获取数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取失败回调接收方");
    }

    @Override
    public void downloadRefreshdataSucceedCompleted() {
        mDownloadAtBinding.swifreshDownloadContent.setRefreshing(false);
        mDownloadAdapter.notifyDataSetChanged();
        Toast.makeText(mDownloadActivity, "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void downloadRefreshdataFailedCompleted() {
        Toast.makeText(mDownloadActivity, "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新失败回调接收方");
    }
}
