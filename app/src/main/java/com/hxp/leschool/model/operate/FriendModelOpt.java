package com.hxp.leschool.model.operate;

import android.content.Intent;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.FriendModel;
import com.hxp.leschool.utils.MyApplication;
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
    private AVQuery<AVObject> avQuery = new AVQuery<>("UserInfo");
    private FriendOptCallback mFriendOptCallback;
    private FriendFragment mFriendFragment;

    public FriendModelOpt(FriendViewModel microblogViewModel, FriendFragment friendFragment) {
        mFriendOptCallback = microblogViewModel;
        mFriendFragment = friendFragment;
    }

    //获取数据
    public void getData() {
        avQuery.selectKeys(Arrays.asList("useNname", "userPassword"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        mFriendModel = new FriendModel();
                        mFriendModel.setUserName(list.get(i).getString("useNname"));
                        mFriendModel.setUserPicture(R.mipmap.ic_launcher);
                        mData.add(mFriendModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt获得数据：" + mData.get(i).getUserName());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "FriendModelOpt");
                    Log.d("fragment", "数据获取成功回调发送方-FriendModelOpt");
                    mFriendOptCallback.friendGetdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据获取失败回调发送方-FriendModelOpt");
                    mFriendOptCallback.friendGetdataFailedCompleted();
                }
            }
        });
    }

    //刷新数据
    public void refreshData() {
        avQuery.selectKeys(Arrays.asList("useNname"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        mFriendModel = new FriendModel();
                        mFriendModel.setUserName(list.get(i).getString("useNname"));
                        mFriendModel.setUserPicture(R.mipmap.ic_launcher);
                        mData.add(mFriendModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt刷新数据：" + mData.get(i).getUserName());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "FriendModelOpt");
                    Log.d("fragment", "数据刷新成功回调发送方-FriendModelOpt");
                    mFriendOptCallback.friendRefreshdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据刷新失败回调发送方-FriendModelOpt");
                    mFriendOptCallback.friendRefreshdataFailedCompleted();
                }
            }
        });
    }

    //打开聊天
    public void chat(int position) {
        String chatFriend = mData.get(position).getUserName();
        Log.d("fragment", "聊天好友是：" + chatFriend);
        Intent intent = new Intent(mFriendFragment.getActivity(), FriendChatActivity.class);
        intent.putExtra("chatFriend", chatFriend);
        mFriendFragment.getActivity().startActivity(intent);
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
