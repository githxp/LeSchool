package com.hxp.leschool.model.operate;

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
                        mSearchFriendModel.setAvatar(R.mipmap.ic_launcher);
                        mData.add(mSearchFriendModel);
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "FriendModelOpt");
                    Log.d("fragment", "数据刷新成功回调发送方-FriendModelOpt");
                    mSearchFriendallback.refresh();
                } else {
                    Log.d("fragment", "数据刷新失败回调发送方-FriendModelOpt");
                    mSearchFriendallback.refreshErr();
                }
            }
        });
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
