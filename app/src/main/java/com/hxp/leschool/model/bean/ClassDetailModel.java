package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;

/**
 * Created by hxp on 17-1-25.
 */

public class ClassDetailModel extends BaseObservable {

    private String title;

    public ClassDetailModel() {
    }

    public ClassDetailModel(String title) {
        this.title = title;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
