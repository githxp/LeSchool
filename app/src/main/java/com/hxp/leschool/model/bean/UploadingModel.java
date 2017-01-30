package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * Created by hxp on 17-1-24.
 */


public class UploadingModel extends BaseObservable {

    private String title;
    private int picture;
    private int uploadProcess = 0;
    private boolean uploadstate = false;

    public UploadingModel() {
    }

    public UploadingModel(String title, int picture,int uploadProcess,boolean uploadstate) {
        this.title = title;
        this.picture = picture;
        this.uploadProcess = uploadProcess;
        this.uploadstate = uploadstate;
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
    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
        notifyPropertyChanged(BR.picture);
    }

    @Bindable
    public int getUploadProcess() {
        return uploadProcess;
    }

    public void setUploadProcess(int uploadProcess) {
        this.uploadProcess = uploadProcess;
        notifyPropertyChanged(BR.uploadProcess);
    }

    @Bindable
    public boolean getUploadstate() {
        return uploadstate;
    }

    public void setUploadstate(boolean uploadstate) {
        this.uploadstate = uploadstate;
        notifyPropertyChanged(BR.uploadstate);
    }
}
