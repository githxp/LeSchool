package com.hxp.leschool.model.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by hxp on 17-1-29.
 */

public class BmobUserModel extends BmobUser{

    private Integer userPicture;

    public Integer getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(Integer userPicture) {
        this.userPicture = userPicture;
    }
}
