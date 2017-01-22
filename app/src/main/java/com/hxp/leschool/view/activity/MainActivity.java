package com.hxp.leschool.view.activity;

import android.app.FragmentTransaction;
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
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyApplication.MicroblogSingleChatCallback;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.view.fragment.MicroblogFragment;
import com.hxp.leschool.view.fragment.MicroblogSingleChatFragment;
import com.hxp.leschool.view.fragment.MineFragment;
import com.hxp.leschool.view.fragment.NearFragment;

public class MainActivity extends AppCompatActivity implements MicroblogSingleChatCallback {

    private ClassFragment mClassFragment;
    private MicroblogFragment mMicroblogFragment;
    private NearFragment mNearFragment;
    private MineFragment mMineFragment;
    private SelecteUploadFileCallback mSelecteUploadFileCallback;
    private MicroblogSingleChatFragment mMicroblogSingleChatFragment;

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

        //跟踪统计应用的打开情况
        AVAnalytics.trackAppOpened(getIntent());

        MyApplication.getInstance().setMainActivity(this);
    }

    public void onMain_Layout_ClassClicked(View view) {
        if (mClassFragment == null) {
            mClassFragment = new ClassFragment();
            Log.d("Fragment生命周期管理", "创建ClassFragment");
        }
        if (!mClassFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mClassFragment).commit();
            Log.d("Fragment生命周期管理", "替换ClassFragment");
        }
    }

    public void onMain_Layout_MicroblogClicked(View view) {
        if (mMicroblogFragment == null) {
            mMicroblogFragment = new MicroblogFragment();
            Log.d("Fragment生命周期管理", "创建MicroblogFragment");
        }
        if (!mMicroblogFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mMicroblogFragment).commit();
            Log.d("Fragment生命周期管理", "替换MicroblogFragment");
        }
    }

    public void onMain_Layout_NearClicked(View view) {
        if (mNearFragment == null) {
            mNearFragment = new NearFragment();
            Log.d("Fragment生命周期管理", "创建NearFragment");
        }
        if (!mNearFragment.isVisible()) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_fms, mNearFragment).commit();
            Log.d("Fragment生命周期管理", "替换NearFragment");
        }
    }

    public void onMain_Layout_MineClicked(View view) {
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

    @Override
    public void microblogSingleChatCallback(String userName) {

        mMicroblogSingleChatFragment = new MicroblogSingleChatFragment();
        mMicroblogSingleChatFragment.setUserName(userName);
        Log.d("fragment", "创建了MicroblogSingleChatFragment");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_main_fms, mMicroblogSingleChatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
