package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendModel extends BaseObservable{

    private String userName;
    private int avatar;

    public FriendModel() {
    }

    public FriendModel(String userName, int userPicture) {
        this.userName = userName;
        this.avatar = userPicture;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int userPicture) {
        this.avatar = userPicture;
        notifyPropertyChanged(BR.userPicture);
    }
}
