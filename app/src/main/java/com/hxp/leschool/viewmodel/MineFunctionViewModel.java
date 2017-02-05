package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.hxp.leschool.adapter.MineFunctionAdapter;
import com.hxp.leschool.databinding.MinefunctionFmBinding;
import com.hxp.leschool.model.opt.MineFunctionModelOpt;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.DownloadActivity;
import com.hxp.leschool.view.activity.LoginAndRegActivity;
import com.hxp.leschool.view.activity.MapActivity;
import com.hxp.leschool.view.fragment.MineFunctionFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class MineFunctionViewModel implements MineFunctionModelOpt.MineFunctionGetdataCallback {

    public MineFunctionModelOpt mMineFunctionModelOpt;
    private MineFunctionFragment mMineFunctionFragment;
    private MinefunctionFmBinding mMinefunctionFmBinding;
    private MineFunctionAdapter mMineFunctionAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public MineFunctionViewModel(final MineFunctionFragment mineFunctionFragment, MinefunctionFmBinding minefunctionFmBinding) {

        mMineFunctionFragment = mineFunctionFragment;
        mMinefunctionFmBinding = minefunctionFmBinding;

        mMineFunctionModelOpt = new MineFunctionModelOpt(this);

        mMineFunctionAdapter = new MineFunctionAdapter(this);

        mMinefunctionFmBinding.setMMineFunctionViewModel(this);

        mLinearLayoutManager = new LinearLayoutManager(mMineFunctionFragment.getActivity(), LinearLayoutManager.VERTICAL, false);
        mMinefunctionFmBinding.rvMineFunction.setLayoutManager(mLinearLayoutManager);
        mMinefunctionFmBinding.rvMineFunction.setAdapter(new RecyclerView.Adapter() {
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

        mMineFunctionModelOpt.getData();
        Log.d("fragment", "用户功能opt获取数据");

        minefunctionFmBinding.setMMineFunctionViewModel(this);

        mMineFunctionAdapter.setOnItemClickListener(new MineFunctionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        mMineFunctionFragment.getActivity().startActivity(new Intent(mMineFunctionFragment.getActivity(), DownloadActivity.class));
                        Toast.makeText(mMineFunctionFragment.getActivity(), "下载", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mMineFunctionFragment.getActivity().startActivity(new Intent(mMineFunctionFragment.getActivity(), MapActivity.class));
                        Toast.makeText(mMineFunctionFragment.getActivity(), "地图", Toast.LENGTH_SHORT).show();
                    case 2:
                        AVUser.getCurrentUser().logOut();
                        if (AVUser.getCurrentUser() == null) {
                            Toast.makeText(mMineFunctionFragment.getActivity(), "退出登陆成功", Toast.LENGTH_SHORT).show();
                            MyApplication.getInstance().getAVIMClient().close(new AVIMClientCallback() {
                                @Override
                                public void done(AVIMClient avimClient, AVIMException e) {
                                    Toast.makeText(mineFunctionFragment.getActivity(), "当前用户已与服务器断开连接", Toast.LENGTH_SHORT).show();
                                }
                            });
                            mMineFunctionFragment.getActivity().startActivity(new Intent(mMineFunctionFragment.getActivity(), LoginAndRegActivity.class));
                            mMineFunctionFragment.getActivity().finish();
                        } else {
                            Toast.makeText(mMineFunctionFragment.getActivity(), "退出登陆失败", Toast.LENGTH_SHORT).show();
                        }
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void mineFuncionGetdataCompleted() {
        mMinefunctionFmBinding.rvMineFunction.setLayoutManager(mLinearLayoutManager);
        mMinefunctionFmBinding.rvMineFunction.setAdapter(mMineFunctionAdapter);
        Log.d("fragment", "数据获取回调接收方-MineFunction");
    }
}
