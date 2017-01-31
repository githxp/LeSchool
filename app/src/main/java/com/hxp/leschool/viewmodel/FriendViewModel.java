package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.FriendAdapter;
import com.hxp.leschool.databinding.FriendFmBinding;
import com.hxp.leschool.model.operate.FriendModelOpt;
import com.hxp.leschool.model.operate.FriendModelOpt.FriendOptCallback;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.activity.SearchFriendActivity;
import com.hxp.leschool.view.fragment.FriendFragment;

import cn.bmob.v3.BmobUser;


/**
 * Created by hxp on 17-1-21.
 */


public class FriendViewModel implements FriendOptCallback {

    public FriendModelOpt mFriendModelOpt;
    private FriendFragment mFriendFragment;
    private FriendFmBinding mFriendFmBinding;
    private FriendAdapter mFriendAdapter;

    public FriendViewModel(FriendFragment friendFragment, FriendFmBinding friendFmBinding) {
        mFriendFragment = friendFragment;
        mFriendFmBinding = friendFmBinding;

        mFriendFmBinding.setMFriendViewModel(this);

        mFriendFmBinding.rvFriendContent.setLayoutManager(new LinearLayoutManager(mFriendFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mFriendFmBinding.rvFriendContent.setAdapter(new RecyclerView.Adapter() {
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

        mFriendFmBinding.swifreshFriendContent.setRefreshing(true);

        mFriendModelOpt = new FriendModelOpt(this, mFriendFragment);
        mFriendAdapter = new FriendAdapter(this);

        mFriendModelOpt.getData();

        mFriendFmBinding.swifreshFriendContent.setProgressViewOffset(true, 0, 50);
        mFriendFmBinding.swifreshFriendContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mFriendFmBinding.swifreshFriendContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mFriendModelOpt.refreshData();
                    }
                }
        );

        mFriendFmBinding.fabFriendAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class) == null) {
                    Toast.makeText(mFriendFragment.getActivity(), "请先登陆", Toast.LENGTH_SHORT).show();
                }else {
                    mFriendFragment.getActivity().startActivity(new Intent(mFriendFragment.getActivity(), SearchFriendActivity.class));
                }
            }
        });

        mFriendAdapter.setOnItemClickListener(new FriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //打开指定好友聊天界面
                Intent intent = new Intent(mFriendFragment.getActivity(), FriendChatActivity.class);
                mFriendFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void friendGetdataSucceedCompleted() {
        mFriendFmBinding.rvFriendContent.setLayoutManager(new LinearLayoutManager(mFriendFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mFriendFmBinding.rvFriendContent.setAdapter(mFriendAdapter);
        mFriendFmBinding.swifreshFriendContent.setRefreshing(false);
        Log.d("fragment", "数据获取成功回调接收方-FriendModelOpt");
    }

    @Override
    public void friendGetdataFailedCompleted() {
        Log.d("fragment", "数据获取失败回调接收方-FriendModelOpt");
    }

    @Override
    public void friendRefreshdataSucceedCompleted() {
        mFriendFmBinding.swifreshFriendContent.setRefreshing(false);
        mFriendAdapter.notifyDataSetChanged();
        Toast.makeText(mFriendFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "数据刷新成功回调接收方-FriendModelOpt");
    }

    @Override
    public void friendRefreshdataFailedCompleted() {
        Log.d("fragment", "数据刷新失败回调接收方-FriendModelOpt");
    }
}
