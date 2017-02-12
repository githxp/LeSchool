package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.hxp.leschool.R;

/**
 * Created by hxp on 17-2-12.
 */

public class CustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
    }
}
