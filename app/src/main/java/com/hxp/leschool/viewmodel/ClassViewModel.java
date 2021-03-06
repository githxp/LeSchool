package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.ClassAdapter;
import com.hxp.leschool.databinding.ClassFmBinding;
import com.hxp.leschool.databinding.ClassItemBinding;
import com.hxp.leschool.model.opt.ClassModelOpt;
import com.hxp.leschool.model.opt.ClassModelOpt.ClassCallback;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.ClassDetailActivity;
import com.hxp.leschool.view.activity.MainActivity.SelecteUploadFileCallback;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.widget.RecycleItemDivider;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassViewModel implements ClassCallback, SelecteUploadFileCallback {

    public ClassModelOpt mClassModelOpt;
    private ClassFmBinding mClassFmBinding;
    private ClassItemBinding mClassItemBinding;
    private ClassFragment mClassFragment;
    public ClassAdapter mClassAdapter;

    public ClassViewModel(ClassFragment classFragment, ClassFmBinding classFmBinding, ClassItemBinding classItemBinding) {

        mClassFragment = classFragment;
        mClassFmBinding = classFmBinding;
        mClassItemBinding = classItemBinding;

        mClassModelOpt = new ClassModelOpt(this);
        mClassAdapter = new ClassAdapter(this, mClassFragment);

        mClassFmBinding.rvClassContent.setLayoutManager(new LinearLayoutManager(mClassFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mClassFmBinding.rvClassContent.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        mClassFmBinding.rvClassContent.setItemAnimator(new DefaultItemAnimator());
        mClassFmBinding.rvClassContent.addItemDecoration(new RecycleItemDivider(mClassFragment.getActivity(), RecycleItemDivider.VERTICAL_LIST));
        mClassFmBinding.swifreshClassContent.setRefreshing(true);

        mClassFmBinding.setMClassViewModel(this);
        mClassItemBinding.setMClassViewModel(this);

        mClassModelOpt.get();

        mClassFmBinding.swifreshClassContent.setProgressViewOffset(true, 0, 50);
        mClassFmBinding.swifreshClassContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mClassFmBinding.swifreshClassContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mClassModelOpt.refresh();
                    }
                }
        );

        mClassFmBinding.fabClassUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //系统调用Action属性
                //intent.setType("application/msword");
                intent.setType("*/*");
                //设置文件类型
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                // 添加Category属性
                if (intent.resolveActivity(MyApplication.getInstance().getPackageManager()) != null) {
                    mClassFragment.getActivity().startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(mClassFragment.getActivity(), "未找到支持的应用", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mClassAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mClassFragment.getActivity(), ClassDetailActivity.class);
                intent.putExtra("classDetailTitle", mClassModelOpt.mData.get(position).getTitle());
                intent.putExtra("classDetailUrl", mClassModelOpt.mData.get(position).getUrl());
                mClassFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void get() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        mClassFmBinding.rvClassContent.setLayoutManager(new LinearLayoutManager(mClassFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mClassFmBinding.rvClassContent.setAdapter(mClassAdapter);
        Log.d("fragment", "ClassModelOpt数据获取成功回调接收方");
    }

    @Override
    public void getErr() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        Toast.makeText(mClassFragment.getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "ClassModelOpt数据获取失败回调接收方");
    }

    @Override
    public void refresh() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        mClassAdapter.notifyDataSetChanged();
        Log.d("fragment", "ClassModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void refreshErr() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        Toast.makeText(mClassFragment.getActivity(), "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "ClassModelOpt数据刷新失败回调接收方");
    }

    @Override
    public void selecteUploadFileCompleted(String filePath) {
        try {
            mClassModelOpt.uploadData(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("fragment", "选择上传文件后回调接收方");
    }
}
