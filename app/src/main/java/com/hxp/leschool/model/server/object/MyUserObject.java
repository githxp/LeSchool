package com.hxp.leschool.model.server.object;

import cn.bmob.v3.BmobObject;

/**
 * Created by hxp on 17-1-30.
 */

public class MyUserObject extends BmobObject {

    private String userName;
    private String userID;
    private String avatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
