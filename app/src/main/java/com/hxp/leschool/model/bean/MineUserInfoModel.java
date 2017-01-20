package com.hxp.leschool.model.bean;

/**
 * Created by hxp on 17-1-19.
 */

public class MineUserInfoModel {

    private String userName;
    private int userPicture;

    public MineUserInfoModel() {
    }

    public MineUserInfoModel(String userName, int userPicture) {
        this.userName = userName;
        this.userPicture = userPicture;
    }

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
