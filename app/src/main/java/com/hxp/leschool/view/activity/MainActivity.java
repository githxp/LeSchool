package com.hxp.leschool.view.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.hxp.leschool.R;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.view.fragment.MicroblogFragment;
import com.hxp.leschool.view.fragment.MineFragment;
import com.hxp.leschool.view.fragment.NearFragment;


public class MainActivity extends AppCompatActivity {

    private ClassFragment mClassFragment;
    private MicroblogFragment mMicroblogFragment;
    private NearFragment mNearFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main_toolbar);
        setSupportActionBar(toolbar);
        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment生命周期管理", "创建ClassFragment");
        }
        getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mClassFragment).commit();
        AVOSCloud.initialize(this, "JXors33cW6wDujTiVDgfJh5x-gzGzoHsz", "AgN648cOdXpbA0HHdBJPBXEc");
        AVAnalytics.trackAppOpened(getIntent());
    }

    public void onMian_Layout_ClassClicked(View view) {
        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment生命周期管理", "创建ClassFragment");
        }
        if (!mClassFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mClassFragment).commit();
            Log.d("Fragment生命周期管理", "替换ClassFragment");
        }
    }

    public void onMian_Layout_MicroblogClicked(View view) {
        if (mMicroblogFragment == null) {
            mMicroblogFragment = new MicroblogFragment();
            Log.d("Fragment生命周期管理", "创建MicroblogFragment");
        }
        if (!mMicroblogFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mMicroblogFragment).commit();
            Log.d("Fragment生命周期管理", "替换MicroblogFragment");
        }
    }

    public void onMian_Layout_NearClicked(View view) {
        if (mNearFragment == null) {
            mNearFragment = new NearFragment();
            Log.d("Fragment生命周期管理", "创建NearFragment");
        }
        if (!mNearFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mNearFragment).commit();
            Log.d("Fragment生命周期管理", "替换NearFragment");
        }
    }

    public void onMian_Layout_MineClicked(View view) {
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            Log.d("Fragment生命周期管理", "创建MineFragment");
        }
        if (!mMineFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mMineFragment).commit();
            Log.d("Fragment生命周期管理", "替换MineFragment");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_setting:
                Toast.makeText(this, "点击菜单项", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
