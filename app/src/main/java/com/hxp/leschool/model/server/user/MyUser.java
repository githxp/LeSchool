package com.hxp.leschool.model.server.user;

import cn.bmob.v3.BmobUser;

/**
 * Created by hxp on 17-1-29.
 */

public class MyUser extends BmobUser{

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
