package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.MicroblogContactsAdapter;
import com.hxp.leschool.databinding.MicroblogFmBinding;
import com.hxp.leschool.model.operate.MicroblogContactsModelOpt;
import com.hxp.leschool.model.operate.MicroblogContactsModelOpt.MicroblogContactsOptCallback;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.fragment.MicroblogFragment;

/**
 * Created by hxp on 17-1-21.
 */

public class MicroblogViewModel implements MicroblogContactsOptCallback {

    public MicroblogContactsModelOpt mMicroblogContactsModelOpt;
    private MicroblogFragment mMicroblogFragment;
    private MicroblogFmBinding mMicroblogFmBinding;
    private MicroblogContactsAdapter mMicroblogContactsAdapter;

    public MicroblogViewModel(MicroblogFragment microblogFragment, MicroblogFmBinding microblogFmBinding) {
        mMicroblogFragment = microblogFragment;
        mMicroblogFmBinding = microblogFmBinding;

        mMicroblogFmBinding.setMMicroblogViewModel(this);

        mMicroblogFmBinding.rvMicroblogContacts.setLayoutManager(new LinearLayoutManager(mMicroblogFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mMicroblogFmBinding.rvMicroblogContacts.setAdapter(new RecyclerView.Adapter() {
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

        mMicroblogFmBinding.swifreshMicroblogContacts.setRefreshing(true);

        mMicroblogContactsModelOpt = new MicroblogContactsModelOpt(this);
        mMicroblogContactsAdapter = new MicroblogContactsAdapter(this);

        mMicroblogContactsModelOpt.getData();

        mMicroblogFmBinding.swifreshMicroblogContacts.setProgressViewOffset(true, 0, 50);
        mMicroblogFmBinding.swifreshMicroblogContacts.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mMicroblogFmBinding.swifreshMicroblogContacts.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mMicroblogContactsModelOpt.refreshData();
                    }
                }
        );

        microblogFmBinding.fabMicroblogAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mMicroblogFragment.getActivity(), "添加好友", Toast.LENGTH_SHORT).show();
            }
        });

        mMicroblogContactsAdapter.setOnItemClickListener(new MicroblogContactsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //打开指定好友聊天界面
                mMicroblogContactsModelOpt.openChat(position);
            }
        });
    }

    @Override
    public void microblogGetdataSucceedCompleted() {
        mMicroblogFmBinding.rvMicroblogContacts.setLayoutManager(new LinearLayoutManager(mMicroblogFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mMicroblogFmBinding.rvMicroblogContacts.setAdapter(mMicroblogContactsAdapter);
        mMicroblogFmBinding.swifreshMicroblogContacts.setRefreshing(false);
        Log.d("fragment", "数据获取成功回调接收方-MicroblogContactsModelOpt");
    }

    @Override
    public void microblogGetdataFailedCompleted() {
        Log.d("fragment", "数据获取失败回调接收方-MicroblogContactsModelOpt");
    }

    @Override
    public void microblogRefreshdataSucceedCompleted() {
        mMicroblogFmBinding.swifreshMicroblogContacts.setRefreshing(false);
        mMicroblogContactsAdapter.notifyDataSetChanged();
        Toast.makeText(mMicroblogFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "数据刷新成功回调接收方-MicroblogContactsModelOpt");
    }

    @Override
    public void microblogRefreshdataFailedCompleted() {
        Log.d("fragment", "数据刷新失败回调接收方-MicroblogContactsModelOpt");
    }
}
