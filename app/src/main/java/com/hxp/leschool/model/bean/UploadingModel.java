package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * Created by hxp on 17-1-24.
 */


public class UploadingModel extends BaseObservable {

    private String title;
    private int avatar;
    private int process = 0;

    public UploadingModel() {
    }

    public UploadingModel(String title, int avatar,int process) {
        this.title = title;
        this.avatar = avatar;
        this.process = process;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
        notifyPropertyChanged(BR.process);
    }
}
