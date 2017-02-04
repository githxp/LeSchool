package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.hxp.leschool.adapter.FriendAdapter;
import com.hxp.leschool.databinding.FriendFmBinding;
import com.hxp.leschool.model.operate.FriendModelOpt;
import com.hxp.leschool.model.operate.FriendModelOpt.FriendCallback;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.activity.SearchFriendActivity;
import com.hxp.leschool.view.fragment.FriendFragment;


/**
 * Created by hxp on 17-1-21.
 */


public class FriendViewModel implements FriendCallback {

    public FriendModelOpt mFriendModelOpt;
    private FriendFragment mFriendFragment;
    private FriendFmBinding mFriendFmBinding;
    private FriendAdapter mFriendAdapter;

    public FriendViewModel(FriendFragment friendFragment, FriendFmBinding friendFmBinding) {
        mFriendFragment = friendFragment;
        mFriendFmBinding = friendFmBinding;

        mFriendFmBinding.setMFriendViewModel(this);

        mFriendModelOpt = new FriendModelOpt(this, mFriendFragment);
        mFriendAdapter = new FriendAdapter(this);

        mFriendFmBinding.rvFriendContent.setLayoutManager(new LinearLayoutManager(mFriendFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mFriendFmBinding.rvFriendContent.setAdapter(mFriendAdapter);

        mFriendFmBinding.swifreshFriendContent.setRefreshing(true);

        mFriendModelOpt.refresh();

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
                        mFriendModelOpt.refresh();
                    }
                }
        );

        mFriendFmBinding.fabFriendAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFriendFragment.getActivity().startActivity(new Intent(mFriendFragment.getActivity(), SearchFriendActivity.class));
            }
        });

        mFriendAdapter.setOnItemClickListener(new FriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //打开指定好友聊天界面
                Intent intent = new Intent(mFriendFragment.getActivity(), FriendChatActivity.class);
                intent.putExtra("userName", mFriendModelOpt.mData.get(position).getUserName());
                mFriendFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void refresh() {
        mFriendFmBinding.swifreshFriendContent.setRefreshing(false);
        mFriendAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据刷新成功回调接收方-FriendModelOpt");
    }

    @Override
    public void refreshErr() {
        Log.d("fragment", "数据刷新失败回调接收方-FriendModelOpt");
    }
}
