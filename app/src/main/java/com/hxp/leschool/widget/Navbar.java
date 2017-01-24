package com.hxp.leschool.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hxp.leschool.R;

/**
 * Created by hxp on 17-1-24.
 */

public class Navbar extends LinearLayout {

    public Navbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.navbar, this);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_navbar_back);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }
}
