package com.hxp.leschool.view.activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.MainBinding;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.view.fragment.MicroblogFragment;
import com.hxp.leschool.view.fragment.MineFragment;
import com.hxp.leschool.view.fragment.NearFragment;
import com.hxp.leschool.viewmodel.MainViewModel;


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
    }

    public void onMian_Layout_ClassClicked(View view) {
        Toast.makeText(this, "ttt1", Toast.LENGTH_SHORT).show();
        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment生命周期管理", "创建ClassFragment");
        }
        getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mClassFragment).commit();
    }

    public void onMian_Layout_MicroblogClicked(View view) {
        Toast.makeText(this, "ttt2", Toast.LENGTH_SHORT).show();
        if (mMicroblogFragment == null) {
            mMicroblogFragment = new MicroblogFragment();
            Log.d("Fragment生命周期管理", "创建MicroblogFragment");
        }
        getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mMicroblogFragment).commit();
    }

    public void onMian_Layout_NearClicked(View view) {
        Toast.makeText(this, "ttt3", Toast.LENGTH_SHORT).show();
        if (mNearFragment == null) {
            mNearFragment = new NearFragment();
            Log.d("Fragment生命周期管理", "创建NearFragment");
        }
        getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mNearFragment).commit();
    }

    public void onMian_Layout_MineClicked(View view) {
        Toast.makeText(this, "ttt4", Toast.LENGTH_SHORT).show();
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            Log.d("Fragment生命周期管理", "创建MineFragment");
        }
        getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mMineFragment).commit();
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
