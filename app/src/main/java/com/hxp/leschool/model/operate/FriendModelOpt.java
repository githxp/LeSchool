package com.hxp.leschool.model.operate;

import android.content.Intent;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.FriendModel;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.fragment.FriendFragment;
import com.hxp.leschool.viewmodel.FriendViewModel;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendModelOpt {

    public FriendModel mFriendModel;
    public ArrayList<FriendModel> mData = new ArrayList<>();
    private FriendOptCallback mFriendOptCallback;
    private FriendFragment mFriendFragment;

    public FriendModelOpt(FriendViewModel microblogViewModel, FriendFragment friendFragment) {
        mFriendOptCallback = microblogViewModel;
        mFriendFragment = friendFragment;
    }

    //获取数据
    public void getData() {
        mFriendModel = new FriendModel();
        mFriendModel.setUserPicture(R.mipmap.ic_launcher);
        mFriendModel.setUserName("测试");
        mData.add(mFriendModel);
        mFriendOptCallback.friendGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {

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
