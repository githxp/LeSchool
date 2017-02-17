package com.hxp.leschool.model.opt;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.SearchFriendModel;
import com.hxp.leschool.viewmodel.SearchFriendViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hxp on 17-1-15.
 */

public class SearchFriendModelOpt {

    public SearchFriendModel mSearchFriendModel;
    public ArrayList<SearchFriendModel> mData = new ArrayList<>();
    private SearchFriendallback mSearchFriendallback;
    private AVQuery<AVObject> avQuery = new AVQuery<>("UserInfo");
    private int mCount = 0;

    public SearchFriendModelOpt(SearchFriendViewModel searchFriendViewModel) {
        mSearchFriendallback = searchFriendViewModel;
    }

    //刷新数据
    public void refresh(String userName) {
        Log.d("fragment", "刷新搜索用户名" + userName);
        avQuery.selectKeys(Arrays.asList("userName", "userPassword"));
        avQuery.orderByDescending("createdAt");
        avQuery.whereNotEqualTo("userName", AVUser.getCurrentUser().getUsername());
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        mSearchFriendModel = new SearchFriendModel();
                        mSearchFriendModel.setUserName(list.get(i).getString("userName"));
                        mData.add(mSearchFriendModel);
                        Log.d("fragment", "查询成功" + list.get(i).getString("userName"));
                    }
                } else {
                    Log.d("fragment", "查询失败");
                }
                Log.d("fragment", "开始查询头像");
                getAvatar();
            }
        });
    }

    //获取头像
    private void getAvatar() {
        avQuery.selectKeys(Arrays.asList("userName", "avatar"));
        for (int i = 0; i < mData.size(); i++) {
            Log.d("fragment", "开始查询" + mData.get(i).getUserName() + "的头像");
            avQuery.whereEqualTo("userName", mData.get(i).getUserName());
            final int j = i;
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        if (list.size() == 1) {
                            mData.get(j).setAvatar(list.get(0).getString("avatar"));
                            Log.d("fragment", j + mData.get(j).getUserName() + "的头像获取成功" + mData.get(j).getAvatar());
                            mCount++;
                            Log.d("fragment", "测试发行");
                            if (mCount == mData.size()) {
                                mCount = 0;
                                Log.d("fragment", "ClassModelOpt数据获取成功回调发送方");
                                mSearchFriendallback.refresh();
                            }
                        } else {
                            Log.d("fragment", "头像获取数量异常");
                        }
                    } else {
                        Log.d("fragment", "ClassModelOpt数据获取失败回调发送方" + e.getMessage());
                        mSearchFriendallback.refreshErr();
                    }
                }
            });
        }
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }


    //刷新数据成功回调
    //刷新数据失败回调
    public interface SearchFriendallback {

        void refresh();

        void refreshErr();
    }
}
