package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;

/**
 * Created by hxp on 17-1-22.
 */

public class MicroblogSingleChatModel extends BaseObservable {

    private String message;
    private boolean isToSend;//t为发送，f为接收

    public MicroblogSingleChatModel() {
    }

    public MicroblogSingleChatModel(String message, boolean isToSend) {
        this.message = message;
        this.isToSend = isToSend;
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    public boolean isToSend() {
        return isToSend;
    }

    public void setToSend(boolean toSend) {
        isToSend = toSend;
    }
}
