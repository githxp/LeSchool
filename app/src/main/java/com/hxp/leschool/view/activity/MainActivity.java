package com.hxp.leschool.view.activity;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.hxp.leschool.R;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.view.fragment.FriendFragment;
import com.hxp.leschool.view.fragment.MineFragment;

public class MainActivity extends AppCompatActivity {

    private ClassFragment mClassFragment;
    private FriendFragment mFriendFragment;
    private MineFragment mMineFragment;
    private SelecteUploadFileCallback mSelecteUploadFileCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main_toolbar);
        setSupportActionBar(toolbar);

        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment", "创建mClassFragment in1");
        }
        getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mClassFragment).commit();
        Log.d("Fragment", "添加mClassFragment in1");

        //跟踪统计应用的打开情况
        AVAnalytics.trackAppOpened(getIntent());
    }

    public void onMain_Layout_ClassClicked(View view) {
        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment", "创建mClassFragment in2");
            getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mClassFragment).commit();
            Log.d("Fragment", "添加mClassFragment in2");
            if (mFriendFragment != null) {
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment in");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragmet in");
            }
        } else if (!mClassFragment.isVisible()) {
            if (mFriendFragment != null) {
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment on");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragment on");
            }
            getFragmentManager().beginTransaction().show(mClassFragment).commit();
            Log.d("Fragment", "显示mClassFragment on");
        }
    }

    public void onMain_Layout_FriendClicked(View view) {
        if (mFriendFragment == null) {
            mFriendFragment = new FriendFragment();
            Log.d("Fragment", "创建mFriendFragment in");
            getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mFriendFragment).commit();
            Log.d("Fragment", "添加mFriendFragment in");
            if (mClassFragment != null) {
                getFragmentManager().beginTransaction().hide(mClassFragment).commit();
                Log.d("Fragment", "隐藏mClassFragment in");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragment in");
            }
        } else if (!mFriendFragment.isVisible()) {
            if (mClassFragment != null) {
                getFragmentManager().beginTransaction().hide(mClassFragment).commit();
                Log.d("Fragment", "隐藏mClassFragment on");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragment on");
            }
            getFragmentManager().beginTransaction().show(mFriendFragment).commit();
            Log.d("Fragment", "显示mFriendFragment on");
        }
    }

    public void onMain_Layout_MineClicked(View view) {
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            Log.d("Fragment", "创建MineFragment in");
            getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mMineFragment).commit();
            Log.d("Fragment", "添加mMineFragment in");
            if (mClassFragment != null) {
                getFragmentManager().beginTransaction().hide(mClassFragment).commit();
                Log.d("Fragment", "隐藏mClassFragment in");
            }
            if (mFriendFragment != null) {
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment in");
            }
        } else if (!mMineFragment.isVisible()) {
            if (mClassFragment != null) {
                getFragmentManager().beginTransaction().hide(mClassFragment).commit();
                Log.d("Fragment", "隐藏mClassFragment on");
            }
            if (mFriendFragment != null) {
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment on");
            }
            getFragmentManager().beginTransaction().show(mMineFragment).commit();
            Log.d("Fragment", "显示mMineFragment on");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
            String filePath = data.getData().getPath();
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            Log.d("fragment", data.getData().getPath());
            Log.d("fragment", fileName);
            mSelecteUploadFileCallback = mClassFragment.mClassViewModel;
            mSelecteUploadFileCallback.selecteUploadFileCompleted(fileName, filePath);
        }
    }

    public interface SelecteUploadFileCallback {
        void selecteUploadFileCompleted(String fileName, String filePath);
    }

    @BindingAdapter("imageres")
    public static void setImageRes(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
