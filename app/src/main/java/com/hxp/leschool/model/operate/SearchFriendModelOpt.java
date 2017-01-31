package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.model.server.object.MyUserObject;
import com.hxp.leschool.model.bean.SearchFriendModel;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.viewmodel.SearchFriendViewModel;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by hxp on 17-1-15.
 */

public class SearchFriendModelOpt {

    public SearchFriendModel mSearchFriendModel;
    public ArrayList<SearchFriendModel> mData = new ArrayList<>();
    private SearchFriendOptCallback mSearchFriendOptCallback;

    public SearchFriendModelOpt(SearchFriendViewModel searchFriendViewModel) {
        mSearchFriendOptCallback = searchFriendViewModel;
    }

    //获取数据
    public void getData(String userName) {
        Log.d("fragment", "获取搜索用户名2" + userName);
        BmobQuery<MyUserObject> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.addWhereEqualTo("userName", userName);
        bmobQuery.addWhereNotEqualTo("userName", BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class).getUsername());
        bmobQuery.findObjects(MyApplication.getInstance(),new FindListener<MyUserObject>() {
            @Override
            public void onSuccess(List<MyUserObject> list) {
                Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.size());
                mData.clear();
                for (int i = 0; i < list.size(); i++) {
                    mSearchFriendModel = new SearchFriendModel();
                    mSearchFriendModel.setUserName(list.get(i).getUserName());
                    mSearchFriendModel.setUserID(list.get(i).getUserID());
                    mSearchFriendModel.setAvatar(list.get(i).getAvatar());
                    mData.add(mSearchFriendModel);
                    Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.get(i).getUserName());
                }
                Log.d("fragment", "SearchFriendModelOpt获取数据回调发送方");
                mSearchFriendOptCallback.searchFriendSearchdataSucceedCompleted();
            }

            @Override
            public void onError(int i, String s) {
                Log.d("fragment", "SearchFriendModelOpt刷新查询失败");
            }
        });
    }

    //刷新数据
    public void refreshData(String userName) {
        Log.d("fragment", "刷新搜索用户名2" + userName);
        BmobQuery<MyUserObject> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.addWhereEqualTo("userName", userName);
        bmobQuery.addWhereNotEqualTo("userName", BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class).getUsername());
        bmobQuery.findObjects(MyApplication.getInstance(),new FindListener<MyUserObject>() {
            @Override
            public void onSuccess(List<MyUserObject> list) {
                Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.size());
                mData.clear();
                for (int i = 0; i < list.size(); i++) {
                    mSearchFriendModel = new SearchFriendModel();
                    //mSearchFriendModel.setUserPicture(R.mipmap.ic_launcher);
                    mSearchFriendModel.setUserName(list.get(i).getUserName());
                    mData.add(mSearchFriendModel);
                    Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.get(i).getUserName());
                }
                Log.d("fragment", "SearchFriendModelOpt刷新数据回调发送方");
                mSearchFriendOptCallback.searchFriendRefreshdataSucceedCompleted();
            }

            @Override
            public void onError(int i, String s) {
                Log.d("fragment", "SearchFriendModelOpt刷新查询失败");
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
