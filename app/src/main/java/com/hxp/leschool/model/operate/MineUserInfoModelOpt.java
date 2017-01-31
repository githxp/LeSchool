package com.hxp.leschool.model.operate;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.MineUserInfoModel;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.MyApplication;

import cn.bmob.v3.BmobUser;


/**
 * Created by hxp on 17-1-15.
 */


public class MineUserInfoModelOpt {

    public MineUserInfoModel mData = new MineUserInfoModel();

    public MineUserInfoModelOpt() {
    }

    //获取数据
    public void getData() {
        //用户查询
        if (BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class) == null) {
            mData.setUserName("登陆/注册");
            mData.setUserPicture(R.mipmap.ic_launcher);
        } else {
            mData.setUserName(BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class).getUsername());
            mData.setUserPicture(R.mipmap.ic_launcher);
        }
    }
}
