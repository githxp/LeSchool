package com.hxp.leschool.model.operate;

import android.content.Intent;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFriendship;
import com.avos.avoscloud.AVFriendshipQuery;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.callback.AVFriendshipCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.model.bean.FriendModel;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.fragment.FriendFragment;
import com.hxp.leschool.viewmodel.FriendViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendModelOpt {

    public FriendModel mFriendModel;
    public ArrayList<FriendModel> mData = new ArrayList<>();
    private FriendOptCallback mFriendOptCallback;
    private FriendFragment mFriendFragment;
    AVQuery<AVObject> avQuery;

    public FriendModelOpt(FriendViewModel microblogViewModel, FriendFragment friendFragment) {
        mFriendOptCallback = microblogViewModel;
        mFriendFragment = friendFragment;
    }

    //获取数据
    public void getData() {
        AVQuery<AVUser> followeeQuery = AVUser.followeeQuery(AVUser.getCurrentUser().getObjectId(), AVUser.class);
        followeeQuery.findInBackground(new FindCallback<AVUser>() {
            @Override
            public void done(List<AVUser> avObjects, AVException avException) {//avObject是好友列表
                mData.clear();
                for (int i = 0; i < avObjects.size(); i++) {
                    avQuery = new AVQuery<>("UserInfo");
                    avQuery.selectKeys(Arrays.asList("userName", "userObjectID"));
                    avQuery.orderByDescending("createdAt");
                    avQuery.whereEqualTo("userObjectID", avObjects.get(i).getObjectId());
                    avQuery.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (list.size() == 1) {
                                mFriendModel = new FriendModel();
                                mFriendModel.setAvatar(R.mipmap.ic_launcher);
                                mFriendModel.setUserName(list.get(0).getString("userName"));
                                Log.d("fragment", "好友：" + list.get(0).getString("userName"));
                                mData.add(mFriendModel);
                            } else {
                                Log.d("fragment", "好友账号异常");
                            }
                            mFriendOptCallback.friendGetdataSucceedCompleted();
                        }
                    });
                }
            }
        });
    }

    //刷新数据
    public void refreshData() {
        AVQuery<AVUser> followeeQuery = AVUser.followeeQuery(AVUser.getCurrentUser().getObjectId(), AVUser.class);
        followeeQuery.findInBackground(new FindCallback<AVUser>() {
            @Override
            public void done(List<AVUser> avObjects, AVException avException) {//avObject是好友列表
                mData.clear();
                for (int i = 0; i < avObjects.size(); i++) {
                    avQuery = new AVQuery<>("UserInfo");
                    avQuery.selectKeys(Arrays.asList("userName", "userObjectID"));
                    avQuery.orderByDescending("createdAt");
                    avQuery.whereEqualTo("userObjectID", avObjects.get(i).getObjectId());
                    avQuery.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            Log.d("fragment", "size:" + list.size());
                            if (list.size() == 1) {
                                mFriendModel = new FriendModel();
                                mFriendModel.setAvatar(R.mipmap.ic_launcher);
                                mFriendModel.setUserName(list.get(0).getString("userName"));
                                Log.d("fragment", "好友：" + list.get(0).getString("userName"));
                                mData.add(mFriendModel);
                            } else {
                                Log.d("fragment", "好友账号异常");
                            }
                            mFriendOptCallback.friendRefreshdataSucceedCompleted();
                        }
                    });
                }
            }
        });
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //获取数据成功回调
    //获取数据失败回调
    //刷新数据成功回调
    //刷新数据失败回调
    public interface FriendOptCallback {
        void friendGetdataSucceedCompleted();

        void friendGetdataFailedCompleted();

        void friendRefreshdataSucceedCompleted();

        void friendRefreshdataFailedCompleted();
    }
}
