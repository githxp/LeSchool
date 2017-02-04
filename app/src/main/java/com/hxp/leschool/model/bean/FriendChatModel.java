package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatModel extends BaseObservable {

    private String msg;
    private boolean isSend;//t为发送，f为接收

    public FriendChatModel() {
    }

    public FriendChatModel(String msg, boolean isSend) {
        this.msg = msg;
        this.isSend = isSend;
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyPropertyChanged(BR.msg);
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean isSend) {
        this.isSend = isSend;
    }
}
