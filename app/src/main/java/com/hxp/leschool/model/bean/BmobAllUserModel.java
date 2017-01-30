package com.hxp.leschool.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by hxp on 17-1-30.
 */

public class BmobAllUserModel extends BmobObject {

    private String userName;
    private int userPicture;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(int userPicture) {
        this.userPicture = userPicture;
    }
}
