package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.avos.avoscloud.AVUser;
import com.hxp.leschool.R;

import cn.bmob.v3.Bmob;

/**
 * Created by hxp on 17-2-1.
 */

public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_at);
        if (AVUser.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginAndRegActivity.class));
            this.finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }

        Bmob.initialize(this, "377ebba633f1a6204e326755191bbc8d","Bomb");

        Log.d("Fragment生命周期管理", "onCreate()触发-IndexActivity");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-IndexActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-IndexActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-IndexActivity");
    }
}
