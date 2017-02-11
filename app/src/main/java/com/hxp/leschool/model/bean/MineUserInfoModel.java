package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;



/**
 * Created by hxp on 17-1-19.
 */


public class MineUserInfoModel extends BaseObservable{

    private String userName;
    private String avatar;

    public MineUserInfoModel() {
    }

    public MineUserInfoModel(String userName, String avatar) {
        this.userName = userName;
        this.avatar = avatar;
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
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }
}
