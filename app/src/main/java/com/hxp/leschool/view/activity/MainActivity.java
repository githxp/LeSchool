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

import com.hxp.leschool.R;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.view.fragment.FriendFragment;
import com.hxp.leschool.view.fragment.MineFragment;

public class MainActivity extends AppCompatActivity {

    private ClassFragment mClassFragment;
    private ConversationFragment mConversationFragment;
    private FriendFragment mFriendFragment;
    private MineFragment mMineFragment;
    private SelecteUploadFileCallback mSelecteUploadFileCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main_toolbar);
        setSupportActionBar(toolbar);

        Log.d("fragment", "创建了MainActivity");

        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment", "创建mClassFragment in1");
        }
        getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mClassFragment).commit();
        Log.d("Fragment", "添加mClassFragment in1");
    }

    public void onMain_Layout_ClassClicked(View view) {
        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment", "创建mClassFragment in2");
            getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mClassFragment).commit();
            Log.d("Fragment", "添加mClassFragment in2");
            if (mConversationFragment != null){
                getFragmentManager().beginTransaction().hide(mConversationFragment).commit();
                Log.d("Fragment", "隐藏mConversationFragment in");
            }
            if (mFriendFragment != null) {
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment in");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragment in");
            }
        } else if (!mClassFragment.isVisible()) {
            if (mConversationFragment != null){
                getFragmentManager().beginTransaction().hide(mConversationFragment).commit();
                Log.d("Fragment", "隐藏mConversationFragment in");
            }
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

    public void onMain_Layout_ConversationClicked(View view){
        if (mConversationFragment == null) {
            mConversationFragment = new ConversationFragment();
            Log.d("Fragment", "创建mConversationFragment in");
            getFragmentManager().beginTransaction().add(R.id.fl_main_fms, mConversationFragment).commit();
            Log.d("Fragment", "添加mConversationFragment in");
            if (mClassFragment != null) {
                getFragmentManager().beginTransaction().hide(mClassFragment).commit();
                Log.d("Fragment", "隐藏mClassFragment in");
            }
            if (mFriendFragment != null){
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment in");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragment in");
            }
        } else if (!mConversationFragment.isVisible()) {
            if (mClassFragment != null) {
                getFragmentManager().beginTransaction().hide(mClassFragment).commit();
                Log.d("Fragment", "隐藏mClassFragment in");
            }
            if (mFriendFragment != null){
                getFragmentManager().beginTransaction().hide(mFriendFragment).commit();
                Log.d("Fragment", "隐藏mFriendFragment in");
            }
            if (mMineFragment != null) {
                getFragmentManager().beginTransaction().hide(mMineFragment).commit();
                Log.d("Fragment", "隐藏mMineFragment in");
            }
            getFragmentManager().beginTransaction().show(mConversationFragment).commit();
            Log.d("Fragment", "显示mConversationFragment on");
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
            if (mConversationFragment != null){
                getFragmentManager().beginTransaction().hide(mConversationFragment).commit();
                Log.d("Fragment", "隐藏mConversationFragment in");
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
            if (mConversationFragment != null){
                getFragmentManager().beginTransaction().hide(mConversationFragment).commit();
                Log.d("Fragment", "隐藏mConversationFragment in");
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
            if (mConversationFragment != null){
                getFragmentManager().beginTransaction().hide(mConversationFragment).commit();
                Log.d("Fragment", "隐藏mConversationFragment in");
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
            if (mConversationFragment != null){
                getFragmentManager().beginTransaction().hide(mConversationFragment).commit();
                Log.d("Fragment", "隐藏mConversationFragment in");
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
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-MainActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-MainActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-MainActivity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
            String filePath = data.getData().getPath();
            Log.d("fragment", data.getData().getPath());
            mSelecteUploadFileCallback = mClassFragment.mClassViewModel;
            mSelecteUploadFileCallback.selecteUploadFileCompleted(filePath);
        }
    }

    public interface SelecteUploadFileCallback {
        void selecteUploadFileCompleted(String filePath);
    }

    @BindingAdapter("imageres")
    public static void setImageRes(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
