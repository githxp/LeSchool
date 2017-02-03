package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.ConversationAdapter;
import com.hxp.leschool.databinding.ConversationFmBinding;
import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.model.operate.ConversationModelOpt;
import com.hxp.leschool.model.operate.ConversationModelOpt.ConversationOptCallback;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.activity.FriendReqActivity;
import com.hxp.leschool.view.fragment.ConversationFragment;


/**
 * Created by hxp on 17-1-21.
 */


public class ConversationViewModel implements ConversationOptCallback {

    public ConversationModelOpt mConversationModelOpt;
    private ConversationFragment mConversationFragment;
    private ConversationFmBinding mConversationFmBinding;
    private ConversationAdapter mConversationAdapter;

    public ConversationViewModel(ConversationFragment conversationFragment, ConversationFmBinding conversationFmBinding) {
        mConversationFragment = conversationFragment;
        mConversationFmBinding = conversationFmBinding;

        mConversationFmBinding.setMConversationViewModel(this);

        mConversationAdapter = new ConversationAdapter(this);

        mConversationFmBinding.rvConversationContent.setLayoutManager(new LinearLayoutManager(mConversationFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mConversationFmBinding.rvConversationContent.setAdapter(mConversationAdapter);

        mConversationFmBinding.swifreshConversationContent.setRefreshing(true);

        mConversationModelOpt = new ConversationModelOpt(this, mConversationFragment);

        mConversationModelOpt.getData();

        mConversationFmBinding.swifreshConversationContent.setProgressViewOffset(true, 0, 50);
        mConversationFmBinding.swifreshConversationContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mConversationFmBinding.swifreshConversationContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mConversationModelOpt.refreshData();
                    }
                }
        );

        mConversationAdapter.setOnItemClickListener(new ConversationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int type = mConversationModelOpt.mData.get(position).getType();
                //打开指定好友聊天界面
                if (type == 1) {
                    //打开处理好友请求页面
                    Intent intent = new Intent(mConversationFragment.getActivity(), FriendReqActivity.class);
                    intent.putExtra("userName", mConversationModelOpt.mData.get(position).getUserName());
                    intent.putExtra("avatar", mConversationModelOpt.mData.get(position).getAvatar());
                    mConversationFragment.getActivity().startActivity(intent);
                    Toast.makeText(mConversationFragment.getActivity(), "处理好友请求", Toast.LENGTH_SHORT).show();
                } else if (type == 2) {
                    Intent intent = new Intent(mConversationFragment.getActivity(), FriendChatActivity.class);
                    intent.putExtra("userName", mConversationModelOpt.mData.get(position).getUserName());
                    mConversationFragment.getActivity().startActivity(intent);
                    Toast.makeText(mConversationFragment.getActivity(), "打开聊天：" + mConversationModelOpt.mData.get(position).getUserName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void conversationGetdataSucceedCompleted() {
        mConversationFmBinding.swifreshConversationContent.setRefreshing(false);
        mConversationAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据获取成功回调接收方-ConversationModelOpt");
    }

    @Override
    public void conversationGetdataFailedCompleted() {
        Log.d("fragment", "数据获取失败回调接收方-ConversationModelOpt");
    }

    @Override
    public void conversationRefreshdataSucceedCompleted() {
        mConversationFmBinding.swifreshConversationContent.setRefreshing(false);
        mConversationAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据刷新成功回调接收方-ConversationModelOpt");
    }

    @Override
    public void conversationRefreshdataFailedCompleted() {
        Log.d("fragment", "数据刷新失败回调接收方-ConversationModelOpt");
    }
}
