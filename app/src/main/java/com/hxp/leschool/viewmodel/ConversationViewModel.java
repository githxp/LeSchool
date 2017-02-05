package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.adapter.ConversationAdapter;
import com.hxp.leschool.databinding.ConversationFmBinding;
import com.hxp.leschool.model.opt.ConversationModelOpt;
import com.hxp.leschool.model.opt.ConversationModelOpt.ConversationCallback;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.ChangeConversationEvent;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.activity.FriendReqActivity;
import com.hxp.leschool.view.fragment.ConversationFragment;


/**
 * Created by hxp on 17-1-21.
 */


public class ConversationViewModel implements ConversationCallback {

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

        mConversationModelOpt.get();

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
                        mConversationModelOpt.get();
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
    public void get() {
        mConversationFmBinding.swifreshConversationContent.setRefreshing(false);
        mConversationAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据刷新成功回调接收方-ConversationModelOpt");
    }

    public void handleAddConversationEvent(AddConversationEvent addConversationEvent) {
        Log.d("fragment", "ConversationViewModel正在处理");
        mConversationModelOpt.handleAddConversationEvent(addConversationEvent);
    }

    public void handleChangeConversationEvent(ChangeConversationEvent changeConversationEvent) {
        Log.d("fragment", "ConversationViewModel正在处理");
        mConversationModelOpt.handleChangeConversationEvent(changeConversationEvent);
    }
}
