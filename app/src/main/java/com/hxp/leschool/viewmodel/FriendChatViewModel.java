package com.hxp.leschool.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.R;
import com.hxp.leschool.adapter.FriendChatAdapter;
import com.hxp.leschool.databinding.FriendchatAtBinding;
import com.hxp.leschool.model.db.bean.opt.ConversationBeanOpt;
import com.hxp.leschool.model.opt.FriendChatModelOpt;
import com.hxp.leschool.model.opt.FriendChatModelOpt.ChatCallback;
import com.hxp.leschool.utils.AvatarHelper;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.event.ChatMsgEvent;
import com.hxp.leschool.view.activity.FriendChatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatViewModel implements ChatCallback {

    public FriendChatModelOpt mFriendChatModelOpt;
    private FriendChatActivity mFriendChatActivity;
    private FriendchatAtBinding mFriendchatAtBinding;
    private FriendChatAdapter mFriendChatAdapter;
    private AVIMConversation mAvimConversation;
    private AVQuery<AVObject> avQuery;
    private String userName;
    public String[] mAvatarData = new String[2];
    private int mCount = 0;

    public FriendChatViewModel(FriendChatActivity friendChatActivity, FriendchatAtBinding friendchatAtBinding) {

        Log.d("fragment", "FriendChatViewModel创建");
        mFriendChatActivity = friendChatActivity;
        mFriendchatAtBinding = friendchatAtBinding;

        mFriendChatModelOpt = new FriendChatModelOpt(this);
        mFriendChatAdapter = new FriendChatAdapter(this, mFriendChatActivity);

        mFriendchatAtBinding.setMFriendChatViewModel(this);

        mFriendchatAtBinding.rvFriendChatMsg.setLayoutManager(new LinearLayoutManager(mFriendChatActivity, LinearLayoutManager.VERTICAL, false));
        mFriendchatAtBinding.rvFriendChatMsg.setAdapter(mFriendChatAdapter);

        mFriendchatAtBinding.swifreshFriendChatContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mFriendChatModelOpt.get(mAvimConversation);
            }
        });

        mFriendChatAdapter.setOnItemClickListener(new FriendChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //聊天消息列表点击事件
            }
        });

        initChat();

        mFriendchatAtBinding.subNavbarFriendChatContent.setTitle(userName);
    }

    public void initChat() {
        userName = mFriendChatActivity.getIntent().getStringExtra("userName");
        Log.d("fragment", "与" + userName + "建立了会话");
        MyApplication.getInstance().setCurrentChatFriend(userName);
        MyApplication.getInstance().getAVIMClient().createConversation(Arrays.asList(userName), userName + "&" + AVUser.getCurrentUser().getUsername(), null, false, true, new AVIMConversationCreatedCallback() {
            @Override
            public void done(AVIMConversation avimConversation, AVIMException e) {
                if (e == null) {
                    mAvimConversation = avimConversation;
                    Toast.makeText(mFriendChatActivity, "已和" + userName + "建立会话", Toast.LENGTH_SHORT).show();
                    initAvatar(userName);
                } else {
                    Toast.makeText(mFriendChatActivity, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btn_FriendChat_sendMsg(View view) {
        final AVIMTextMessage msg = new AVIMTextMessage();
        final String msgContent = mFriendchatAtBinding.etFriendChatMsg.getText().toString();
        if (msgContent.equals("")) {
            Toast.makeText(mFriendChatActivity, "请先输入内容", Toast.LENGTH_SHORT).show();
        } else {
            msg.setText(msgContent);
            mAvimConversation.sendMessage(msg, new AVIMConversationCallback() {
                @Override
                public void done(AVIMException e) {
                    mFriendchatAtBinding.etFriendChatMsg.setText("");
                    mFriendChatModelOpt.setMsg(msg.getText(), true);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    String lastTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                    ConversationBeanOpt.getInstance().changeORadd(userName, msgContent, lastTime, 1, 2);
                }
            });
        }
    }

    private void initAvatar(String userName) {
        avQuery = new AVQuery<>("UserInfo");
        avQuery.selectKeys(Arrays.asList("userName", "avatar"));
        for (int i = 0; i < mAvatarData.length; i++) {
            if (i == 0) {
                avQuery.whereEqualTo("userName", AVUser.getCurrentUser().getUsername());
            } else {
                avQuery.whereEqualTo("userName", userName);
            }
            final int finalI = i;
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        if (list.size() == 1) {
                            mAvatarData[finalI] = list.get(0).getString("avatar");
                            Log.d("fragment", "的头像获取成功" + list.get(0).getString("avatar"));
                            mCount++;
                            Log.d("fragment", "测试发行" + "mcount:" + mCount);
                            if (mCount == mAvatarData.length) {
                                mCount = 0;
                                Log.d("fragment", "发行");
                                mFriendChatModelOpt.get(mAvimConversation);
                            }
                        } else {
                            Log.d("fragment", "头像获取数量异常");
                        }
                    } else {
                        Log.d("fragment", "ClassModelOpt数据刷新失败回调发送方" + e.getMessage());
                    }
                }
            });
        }
    }


    @Override
    public void refresh() {
        mFriendChatAdapter.notifyDataSetChanged();
        mFriendchatAtBinding.swifreshFriendChatContent.setRefreshing(false);
        mFriendchatAtBinding.rvFriendChatMsg.scrollToPosition(mFriendChatModelOpt.getCount() - 1);
        Log.d("fragment", "FriendChatModelOpt消息发送成功回调接收方");
    }


    //处理新消息
    public void handleChatMsgEvent(ChatMsgEvent chatMsgEvent) {
        Log.d("fragment", "交给mFriendChatModelOpt处理" + chatMsgEvent.getMsg());
        mFriendChatModelOpt.handleChatMsgEvent(chatMsgEvent);
    }
}
