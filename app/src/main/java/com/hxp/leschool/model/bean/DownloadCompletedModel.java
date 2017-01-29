package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * Created by hxp on 17-1-24.
 */


public class DownloadCompletedModel extends BaseObservable {

    private String title;
    private int picture;

    public DownloadCompletedModel() {
    }

    public DownloadCompletedModel(String title, int picture) {
        this.title = title;
        this.picture = picture;
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
}
