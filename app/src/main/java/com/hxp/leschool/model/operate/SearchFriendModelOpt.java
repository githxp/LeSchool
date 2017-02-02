package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.FriendModel;
import com.hxp.leschool.model.bean.SearchFriendModel;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.viewmodel.SearchFriendViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by hxp on 17-1-15.
 */

public class SearchFriendModelOpt {

    public SearchFriendModel mSearchFriendModel;
    public ArrayList<SearchFriendModel> mData = new ArrayList<>();
    private SearchFriendOptCallback mSearchFriendOptCallback;
    private AVQuery<AVObject> avQuery = new AVQuery<>("UserInfo");

    public SearchFriendModelOpt(SearchFriendViewModel searchFriendViewModel) {
        mSearchFriendOptCallback = searchFriendViewModel;
    }

    //获取数据
    public void getData(String userName) {
        Log.d("fragment", "获取搜索用户名2" + userName);
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
                        mSearchFriendModel.setAvatar(R.mipmap.ic_launcher);
                        mData.add(mSearchFriendModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt获得数据：" + mData.get(i).getUserName());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "FriendModelOpt");
                    Log.d("fragment", "数据获取成功回调发送方-FriendModelOpt");
                    mSearchFriendOptCallback.searchFriendSearchdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据获取失败回调发送方-FriendModelOpt");
                    mSearchFriendOptCallback.searchFriendSearchdataFailedCompleted();
                }
            }
        });
    }

    //刷新数据
    public void refreshData(String userName) {
        Log.d("fragment", "刷新搜索用户名2" + userName);
        avQuery.selectKeys(Arrays.asList("userNname", "userPassword"));
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
                        mSearchFriendModel.setUserName(list.get(i).getString("useNname"));
                        mSearchFriendModel.setAvatar(R.mipmap.ic_launcher);
                        mData.add(mSearchFriendModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt获得数据：" + mData.get(i).getUserName());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "FriendModelOpt");
                    Log.d("fragment", "数据刷新成功回调发送方-FriendModelOpt");
                    mSearchFriendOptCallback.searchFriendRefreshdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据刷新失败回调发送方-FriendModelOpt");
                    mSearchFriendOptCallback.searchFriendRefreshdataFailedCompleted();
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
    public interface SearchFriendOptCallback {
        void searchFriendSearchdataSucceedCompleted();

        void searchFriendSearchdataFailedCompleted();

        void searchFriendRefreshdataSucceedCompleted();

        void searchFriendRefreshdataFailedCompleted();
    }
}
