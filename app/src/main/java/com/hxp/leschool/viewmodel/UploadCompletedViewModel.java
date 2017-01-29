/*
package com.hxp.leschool.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.UploadCompletedAdapter;
import com.hxp.leschool.databinding.UploadcompletedFmBinding;
import com.hxp.leschool.databinding.UploadcompletedItemBinding;
import com.hxp.leschool.model.operate.UploadCompletedModelOpt.UploadCompletedOptCallback;
import com.hxp.leschool.model.operate.UploadCompletedModelOpt;
import com.hxp.leschool.view.fragment.UploadCompletedFragment;


*/
/**
 * Created by hxp on 17-1-13.
 *//*


public class UploadCompletedViewModel implements UploadCompletedOptCallback {

    public UploadCompletedModelOpt mUploadCompletedModelOpt;
    private UploadCompletedFragment mUploadCompletedFragment;
    private UploadcompletedFmBinding mUploadcompletedFmBinding;
    private UploadcompletedItemBinding mUploadcompletedItemBinding;
    private UploadCompletedAdapter mUploadCompletedAdapter;

    public UploadCompletedViewModel(UploadCompletedFragment uploadCompletedFragment, UploadcompletedFmBinding uploadcompletedFmBinding, UploadcompletedItemBinding uploadcompletedItemBinding) {

        mUploadCompletedFragment = uploadCompletedFragment;
        mUploadcompletedFmBinding = uploadcompletedFmBinding;
        mUploadcompletedItemBinding = uploadcompletedItemBinding;

        mUploadcompletedFmBinding.rvUploadCompletedContent.setLayoutManager(new LinearLayoutManager(mUploadCompletedFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mUploadcompletedFmBinding.rvUploadCompletedContent.setAdapter(new RecyclerView.Adapter() {
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
        mUploadcompletedFmBinding.swifreshUploadCompletedContent.setRefreshing(true);

        mUploadCompletedAdapter = new UploadCompletedAdapter(this);
        mUploadCompletedModelOpt = new UploadCompletedModelOpt(this, mUploadCompletedFragment);

        mUploadcompletedFmBinding.setMUploadCompletedViewModel(this);
        mUploadcompletedItemBinding.setMUploadCompletedViewModel(this);

        mUploadCompletedModelOpt.getData();

        mUploadcompletedFmBinding.swifreshUploadCompletedContent.setProgressViewOffset(true, 0, 50);
        mUploadcompletedFmBinding.swifreshUploadCompletedContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mUploadcompletedFmBinding.swifreshUploadCompletedContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mUploadCompletedModelOpt.refreshData();
                    }
                }
        );

        mUploadCompletedAdapter.setOnItemClickListener(new UploadCompletedAdapter.OnItemClickListener() {
            @Override
            public void onItemDelRecordClick(View view, int position) {
                //删除上传记录
                mUploadCompletedModelOpt.delRecord(position);
            }
        });
    }

    @Override
    public void uploadCompletedGetdataSucceedCompleted() {
        mUploadcompletedFmBinding.rvUploadCompletedContent.setLayoutManager(new LinearLayoutManager(mUploadCompletedFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mUploadcompletedFmBinding.rvUploadCompletedContent.setAdapter(mUploadCompletedAdapter);
        mUploadcompletedFmBinding.swifreshUploadCompletedContent.setRefreshing(false);
        Toast.makeText(mUploadCompletedFragment.getActivity(), "获取数据成功", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取成功回调接收方");
    }

    @Override
    public void uploadCompletedGetdataFailedCompleted() {
        Toast.makeText(mUploadCompletedFragment.getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据获取失败回调接收方");
    }

    @Override
    public void uploadCompletedRefreshdataSucceedCompleted() {
        mUploadcompletedFmBinding.swifreshUploadCompletedContent.setRefreshing(false);
        mUploadCompletedAdapter.notifyDataSetChanged();
        Toast.makeText(mUploadCompletedFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void uploadCompletedRefreshdataFailedCompleted() {
        Toast.makeText(mUploadCompletedFragment.getActivity(), "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "DownloadModelOpt数据刷新失败回调接收方");
    }
}
*/
