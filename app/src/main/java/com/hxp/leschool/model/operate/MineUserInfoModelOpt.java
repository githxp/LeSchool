package com.hxp.leschool.model.operate;

import com.avos.avoscloud.AVUser;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.MineUserInfoModel;

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
        if (AVUser.getCurrentUser() == null) {
            mData.setUserPicture(R.mipmap.ic_launcher);
            mData.setUserName("登陆/注册");
        } else {
            mData.setUserPicture(R.mipmap.ic_launcher);
            mData.setUserName(AVUser.getCurrentUser().getUsername());
        }
    }
}
