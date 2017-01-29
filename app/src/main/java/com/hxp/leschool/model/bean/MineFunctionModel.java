package com.hxp.leschool.model.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;


/**
 * Created by hxp on 17-1-13.
 */


public class MineFunctionModel {

    private String functionTitle;
    private int functionPicture;

    public MineFunctionModel(String functionTitle, int functionPicture) {
        this.functionTitle = functionTitle;
        this.functionPicture = functionPicture;
    }

    public int getFunctionPicture() {
        return functionPicture;
    }

    public void setFunctionPicture(int functionPicture) {
        this.functionPicture = functionPicture;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }
}
