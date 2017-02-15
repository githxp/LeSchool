package com.hxp.leschool.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxp.leschool.R;

/**
 * Created by hxp on 17-1-24.
 */

public class MainNavbar extends LinearLayout {

    public MainNavbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.mainnavbar, this);
    }
}
