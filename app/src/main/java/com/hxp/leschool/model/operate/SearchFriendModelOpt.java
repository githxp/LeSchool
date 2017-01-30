package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.BmobAllUserModel;
import com.hxp.leschool.model.bean.BmobClassModel;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.model.bean.SearchFriendModel;
import com.hxp.leschool.model.bean.UploadTaskModel;
import com.hxp.leschool.utils.UploadingPublish;
import com.hxp.leschool.viewmodel.ClassViewModel;
import com.hxp.leschool.viewmodel.SearchFriendViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

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
        BmobQuery<BmobAllUserModel> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.addWhereEqualTo("userName", userName);
        bmobQuery.addWhereNotEqualTo("userName", BmobUser.getCurrentUser().getUsername());
        bmobQuery.findObjects(new FindListener<BmobAllUserModel>() {
            @Override
            public void done(List<BmobAllUserModel> list, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.size());
                    mData.clear();
                    for (int i = 0; i < list.size(); i++) {
                        mSearchFriendModel = new SearchFriendModel();
                        mSearchFriendModel.setUserPicture(R.mipmap.ic_launcher);
                        mSearchFriendModel.setUserName(list.get(i).getUserName());
                        mData.add(mSearchFriendModel);
                        Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.get(i).getUserName());
                    }
                    Log.d("fragment", "SearchFriendModelOpt获取数据回调发送方");
                    mSearchFriendOptCallback.searchFriendSearchdataSucceedCompleted();
                } else {
                    Log.d("fragment", "SearchFriendModelOpt刷新查询失败");
                }
            }
        });
    }

    //刷新数据
    public void refreshData(String userName) {
        Log.d("fragment", "刷新搜索用户名2" + userName);
        BmobQuery<BmobAllUserModel> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.addWhereEqualTo("userName", userName);
        bmobQuery.addWhereNotEqualTo("userName", BmobUser.getCurrentUser().getUsername());
        bmobQuery.findObjects(new FindListener<BmobAllUserModel>() {
            @Override
            public void done(List<BmobAllUserModel> list, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.size());
                    mData.clear();
                    for (int i = 0; i < list.size(); i++) {
                        mSearchFriendModel = new SearchFriendModel();
                        mSearchFriendModel.setUserPicture(R.mipmap.ic_launcher);
                        mSearchFriendModel.setUserName(list.get(i).getUserName());
                        mData.add(mSearchFriendModel);
                        Log.d("fragment", "SearchFriendModelOpt刷新查询成功" + list.get(i).getUserName());
                    }
                    Log.d("fragment", "SearchFriendModelOpt刷新数据回调发送方");
                    mSearchFriendOptCallback.searchFriendRefreshdataSucceedCompleted();
                } else {
                    Log.d("fragment", "SearchFriendModelOpt刷新查询失败");
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
