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
import com.hxp.leschool.model.operate.ClassModelOpt;
import com.hxp.leschool.model.operate.ClassModelOpt.ClassGetdataCallback;
import com.hxp.leschool.model.operate.ClassModelOpt.ClassRefreshdataCallback;
import com.hxp.leschool.view.activity.MainActivity;
import com.hxp.leschool.view.fragment.ClassFragment;

import java.io.FileNotFoundException;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassViewModel implements ClassGetdataCallback, ClassRefreshdataCallback, MainActivity.SelecteUploadFileCallback {

    public ClassModelOpt mClassModelOpt;
    private ClassFmBinding mClassFmBinding;
    private ClassFragment mClassFragment;
    public ClassAdapter mClassAdapter;

    public ClassViewModel(final ClassFragment classFragment, ClassFmBinding classFmBinding) {

        mClassFmBinding = classFmBinding;
        mClassFragment = classFragment;

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
                        Toast.makeText(mClassFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        mClassFmBinding.fabClassUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //系统调用Action属性
                //intent.setType("application/msword");
                intent.setType("application/vnd.ms-excel");
                //设置文件类型
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                // 添加Category属性
                try {
                    mClassFragment.getActivity().startActivityForResult(intent, 1);
                } catch (Exception e) {
                    Toast.makeText(mClassFragment.getActivity(), "没有正确打开文件管理器" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mClassAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mClassModelOpt.downloadData(position);
                Toast.makeText(mClassFragment.getActivity(), "click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void classGetdataCompleted() {
        mClassFmBinding.rvClassContent.setLayoutManager(new LinearLayoutManager(mClassFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mClassFmBinding.rvClassContent.setAdapter(mClassAdapter);
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        Log.d("fragment", "数据获取回调接收方");
    }

    @Override
    public void classRefreshdataCompleted() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        mClassAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据刷新回调接收方");
    }

    @Override
    public void selecteUploadFileCompleted(String fileName, String filePath) {
        try {
            mClassModelOpt.uploadData(fileName, filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("fragment", "选择上传文件后回调接收方");
    }
}
