package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;

/**
 * Created by hxp on 17-1-22.
 */

public class MicroblogContactsModel extends BaseObservable{

    private String userName;
    private int userPicture;

    public MicroblogContactsModel() {
    }

    public MicroblogContactsModel(String userName, int userPicture) {
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
