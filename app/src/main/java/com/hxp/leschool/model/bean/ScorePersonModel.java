package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;

/**
 * Created by hxp on 17-2-9.
 */

public class ScorePersonModel extends BaseObservable{

    private String stuNum;
    private String stuName;

    public ScorePersonModel() {
    }

    public ScorePersonModel(String stuName, String stuNum) {
        this.stuName = stuName;
        this.stuNum = stuNum;
    }

    @Bindable
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
        notifyPropertyChanged(BR.stuName);
    }

    @Bindable
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
        notifyPropertyChanged(BR.stuNum);
    }
}
