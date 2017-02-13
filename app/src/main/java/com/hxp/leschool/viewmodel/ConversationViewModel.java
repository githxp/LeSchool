package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hxp.leschool.adapter.ConversationAdapter;
import com.hxp.leschool.databinding.ConversationFmBinding;
import com.hxp.leschool.model.db.bean.opt.ConversationBeanOpt;
import com.hxp.leschool.model.opt.ConversationModelOpt;
import com.hxp.leschool.model.opt.ConversationModelOpt.ConversationCallback;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.ChangeConversationEvent;
import com.hxp.leschool.utils.event.DelConversationEvent;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.activity.FriendReqActivity;
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.widget.RecycleItemDivider;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;


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

        mConversationModelOpt = new ConversationModelOpt(this, mConversationFragment);
        mConversationAdapter = new ConversationAdapter(this, mConversationFragment);

        mConversationFmBinding.rvConversationContent.setLayoutManager(new LinearLayoutManager(mConversationFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mConversationFmBinding.rvConversationContent.setAdapter(new RecyclerView.Adapter() {
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
        mConversationFmBinding.rvConversationContent.setItemAnimator(new DefaultItemAnimator());
        mConversationFmBinding.rvConversationContent.addItemDecoration(new RecycleItemDivider(mConversationFragment.getActivity(),RecycleItemDivider.VERTICAL_LIST));
        mConversationFmBinding.swifreshConversationContent.setRefreshing(true);

        mConversationFmBinding.rvConversationContent.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mConversationFragment.getActivity())
                        .setBackgroundColor(Color.RED)
                        .setText("删除") // 文字
                        .setTextColor(Color.WHITE) // 文字颜色
                        .setTextSize(16) // 文字大小
                        .setWidth(260)
                        .setHeight(LinearLayout.LayoutParams.MATCH_PARENT);

                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单
            }
        });
        mConversationFmBinding.rvConversationContent.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                ConversationBeanOpt.getInstance().delete(ConversationViewModel.this.mConversationModelOpt.mData.get(adapterPosition).getUserName());
                ConversationViewModel.this.mConversationModelOpt.mData.remove(adapterPosition);
                ConversationViewModel.this.mConversationAdapter.notifyItemRemoved(adapterPosition);
                ConversationViewModel.this.mConversationAdapter.notifyItemRangeChanged(adapterPosition, ConversationViewModel.this.mConversationModelOpt.mData.size() - adapterPosition);
                Log.d("fragment", "adapterposition:" + adapterPosition + " menuposition" + menuPosition);
            }
        });

        mConversationFmBinding.setMConversationViewModel(this);

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
                        mConversationModelOpt.refresh();
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
        mConversationFmBinding.rvConversationContent.setLayoutManager(new LinearLayoutManager(mConversationFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mConversationFmBinding.rvConversationContent.setAdapter(mConversationAdapter);
        Log.d("fragment", "数据刷新成功回调接收方-ConversationModelOpt");
    }

    @Override
    public void refresh() {
        mConversationFmBinding.swifreshConversationContent.setRefreshing(false);
        mConversationAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据刷新成功回调接收方-ConversationModelOpt");
    }

    public void handleAddConversationEvent(AddConversationEvent addConversationEvent) {
        Log.d("fragment", "ConversationViewModel正在处理-添加");
        mConversationModelOpt.handleAddConversationEvent(addConversationEvent);
    }

    public void handleChangeConversationEvent(ChangeConversationEvent changeConversationEvent) {
        Log.d("fragment", "ConversationViewModel正在处理-修改");
        mConversationModelOpt.handleChangeConversationEvent(changeConversationEvent);
    }

    public void handleDelConversationEvent(DelConversationEvent delConversationEvent) {
        Log.d("fragment", "ConversationViewModel正在处理-删除");
        mConversationModelOpt.handleDelConversationEvent(delConversationEvent);
    }
}
