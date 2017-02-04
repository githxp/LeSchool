package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.FollowCallback;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.databinding.FriendreqAtBinding;
import com.hxp.leschool.model.bean.FriendReqModel;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.FriendReqActivity;

import java.util.Arrays;
import java.util.List;


/**
 * 加好友请求处理
 * Created by hxp on 17-1-22.
 */


public class FriendReqViewModel{

    public FriendReqModel mFriendReqModel = new FriendReqModel();
    private FriendReqActivity mFriendReqActivity;
    private FriendreqAtBinding mFriendreqAtBinding;
    AVQuery<AVObject> avQuery = new AVQuery<>("UserInfo");
    private AVIMConversation mAvimConversation;
    private String userObjectID;
    String userName;

    public FriendReqViewModel(FriendReqActivity friendReqActivity, FriendreqAtBinding friendreqAtBinding) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mFriendReqActivity = friendReqActivity;
        mFriendreqAtBinding = friendreqAtBinding;

        mFriendReqModel.setAvatar(mFriendReqActivity.getIntent().getIntExtra("avatar", R.mipmap.ic_launcher));
        mFriendReqModel.setUserName(mFriendReqActivity.getIntent().getStringExtra("userName"));

        mFriendreqAtBinding.setMFriendReqViewModel(this);

        initChat();
    }

    public void initChat() {
        userName = mFriendReqModel.getUserName();
        Log.d("fragment", "与" + userName + "建立了会话");
        MyApplication.getInstance().getAVIMClient().createConversation(Arrays.asList(userName), userName + "&" + AVUser.getCurrentUser().getUsername(), null, new AVIMConversationCreatedCallback() {
            @Override
            public void done(AVIMConversation avimConversation, AVIMException e) {
                mAvimConversation = avimConversation;
            }
        });
    }

    public void on_FriendReq_RefuseClicked(View view) {
        Toast.makeText(mFriendReqActivity, "已拒绝", Toast.LENGTH_SHORT).show();

    }

    public void on_FriendReq_AgreeClicked(View view) {
        avQuery.selectKeys(Arrays.asList("userName", "userObjectID"));
        avQuery.orderByDescending("createdAt");
        avQuery.whereEqualTo("userName", userName);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list.size() == 1) {
                    Log.d("fragment", "账号正常");
                    userObjectID = list.get(0).getString("userObjectID");
                    AVUser.getCurrentUser().followInBackground(userObjectID, new FollowCallback() {
                        @Override
                        public void done(AVObject avObject, AVException e) {
                            Log.d("fragment", "已同意");
                        }

                        @Override
                        protected void internalDone0(Object o, AVException e) {

                        }
                    });
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        Log.d("fragment", "id is:" + list.get(i).getString("userObjectID") + " size is" + list.size());
                    }
                }
            }
        });
    }
}
