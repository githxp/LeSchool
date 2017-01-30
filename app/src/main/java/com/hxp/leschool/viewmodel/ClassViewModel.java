package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.ClassAdapter;
import com.hxp.leschool.databinding.ClassFmBinding;
import com.hxp.leschool.databinding.ClassItemBinding;
import com.hxp.leschool.model.operate.ClassModelOpt;
import com.hxp.leschool.model.operate.ClassModelOpt.ClassOptCallback;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.ClassDetailActivity;
import com.hxp.leschool.view.activity.MainActivity.SelecteUploadFileCallback;
import com.hxp.leschool.view.fragment.ClassFragment;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassViewModel implements ClassOptCallback, SelecteUploadFileCallback {

    public ClassModelOpt mClassModelOpt;
    private ClassFmBinding mClassFmBinding;
    private ClassItemBinding mClassItemBinding;
    private ClassFragment mClassFragment;
    public ClassAdapter mClassAdapter;

    public ClassViewModel(ClassFragment classFragment, ClassFmBinding classFmBinding, ClassItemBinding classItemBinding) {

        mClassFragment = classFragment;
        mClassFmBinding = classFmBinding;
        mClassItemBinding = classItemBinding;

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
        mClassFmBinding.swifreshClassContent.setRefreshing(true);

        mClassModelOpt = new ClassModelOpt(this);
        mClassAdapter = new ClassAdapter(this);

        mClassFmBinding.setMClassViewModel(this);
        mClassItemBinding.setMClassViewModel(this);

        mClassModelOpt.getData();

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
                        mClassModelOpt.refreshData();
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
//                try {
//                    mClassFragment.getActivity().startActivityForResult(intent, 1);
//                } catch (Exception e) {
//                    Toast.makeText(mClassFragment.getActivity(), "没有正确打开文件管理器" + , Toast.LENGTH_SHORT).show();
//                }
            }
        });

        mClassAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mClassFragment.getActivity(), ClassDetailActivity.class);
                intent.putExtra("classTitle", mClassModelOpt.mData.get(position).getTitle());
                intent.putExtra("classUrl",mClassModelOpt.mData.get(position).getUrl());
                mClassFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void classGetdataSucceedCompleted() {
        mClassFmBinding.rvClassContent.setLayoutManager(new LinearLayoutManager(mClassFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mClassFmBinding.rvClassContent.setAdapter(mClassAdapter);
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        Log.d("fragment", "ClassModelOpt数据获取成功回调接收方");
    }

    @Override
    public void classGetdataFailedCompleted() {
        Log.d("fragment", "ClassModelOpt数据获取失败回调接收方");
    }

    @Override
    public void classRefreshdataSucceedCompleted() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        mClassAdapter.notifyDataSetChanged();
        Log.d("fragment", "ClassModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void classRefreshdataFailedCompleted() {
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
