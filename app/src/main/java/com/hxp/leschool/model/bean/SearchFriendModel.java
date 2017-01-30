package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by hxp on 17-1-30.
 */

public class SearchFriendModel extends BaseObservable {

    private String userName;
    private int userPicture;

    public SearchFriendModel() {
    }

    public SearchFriendModel(String userName, int userPicture) {
        this.userName = userName;
        this.userPicture = userPicture;
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
    public int getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(int userPicture) {
        this.userPicture = userPicture;
        notifyPropertyChanged(BR.userPicture);
    }
}
