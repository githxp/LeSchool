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

public class SubNavbar extends LinearLayout {

    private TextView mTextView;

    public SubNavbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.subnavbar, this);
        mTextView = (TextView) findViewById(R.id.tv_SubNavbar_title);
        findViewById(R.id.img_SubNavbar_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setTitle(String title){
        mTextView.setText(title);
    }
}
