package com.hxp.leschool.model.opt;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.MineUserInfoModel;
import com.hxp.leschool.viewmodel.MineUserInfoViewModel;

import java.util.Arrays;
import java.util.List;


/**
 * Created by hxp on 17-1-15.
 */


public class MineUserInfoModelOpt {

    public MineUserInfoModel mData = new MineUserInfoModel();
    private MineUserInfoCallback mMineUserInfoCallback;
    private AVQuery<AVObject> avQuery;

    public MineUserInfoModelOpt(MineUserInfoViewModel mineUserInfoViewModel) {
        mMineUserInfoCallback = mineUserInfoViewModel;
    }

    //获取数据
    public void get() {
        mData.setUserName(AVUser.getCurrentUser().getUsername());
        avQuery = new AVQuery<>("UserInfo");
        avQuery.selectKeys(Arrays.asList("userName", "avatar"));
        avQuery.whereEqualTo("userName", AVUser.getCurrentUser().getUsername());
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    if (list.size() == 1) {
                        mData.setAvatar(list.get(0).getString("avatar"));
                        Log.d("fragment", "头像获取成功" + mData.getAvatar());
                    } else {
                        Log.d("fragment", "头像获取数量异常");
                    }
                } else {
                    Log.d("fragment", "头像获取失败");
                }
                Log.d("fragment", "MineUserInfoOpt发送回调");
                mMineUserInfoCallback.get(mData.getAvatar());
            }
        });
    }

    public interface MineUserInfoCallback {
        void get(String avatarUrl);
    }
}
