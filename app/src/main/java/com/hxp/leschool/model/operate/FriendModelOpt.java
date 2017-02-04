package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.FriendModel;
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
    private FriendCallback mFriendCallback;
    private FriendFragment mFriendFragment;
    AVQuery<AVObject> avQuery;

    public FriendModelOpt(FriendViewModel microblogViewModel, FriendFragment friendFragment) {
        mFriendCallback = microblogViewModel;
        mFriendFragment = friendFragment;
    }

    //刷新数据
    public void refresh() {
        AVQuery<AVUser> followeeQuery = AVUser.followeeQuery(AVUser.getCurrentUser().getObjectId(), AVUser.class);
        followeeQuery.findInBackground(new FindCallback<AVUser>() {
            @Override
            public void done(List<AVUser> avObjects, AVException avException) {//avObject是好友列表
                mData.clear();
                avQuery = new AVQuery<>("UserInfo");
                avQuery.selectKeys(Arrays.asList("userName", "userObjectID"));
                avQuery.orderByDescending("createdAt");
                for (int i = 0; i < avObjects.size(); i++) {
                    avQuery.whereEqualTo("userObjectID", avObjects.get(i).getObjectId());
                    avQuery.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (e == null) {
                                Log.d("fragment", "size:" + list.size());
                                if (list.size() == 1) {
                                    mFriendModel = new FriendModel();
                                    mFriendModel.setAvatar(R.mipmap.ic_launcher);
                                    mFriendModel.setUserName(list.get(0).getString("userName"));
                                    Log.d("fragment", "好友：" + list.get(0).getString("userName"));
                                    mData.add(mFriendModel);
                                    mFriendCallback.refresh();
                                } else {
                                    Log.d("fragment", "好友账号异常");
                                }
                            } else {
                                Log.d("fragment", "刷新用户异常");
                                mFriendCallback.refreshErr();
                            }
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
    public interface FriendCallback {

        void refresh();

        void refreshErr();
    }
}
