package com.hxp.leschool.model.opt;

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
    public void get() {
        mData.setAvatar(R.drawable.ic_avatar);
        mData.setUserName(AVUser.getCurrentUser().getUsername());
    }
}
