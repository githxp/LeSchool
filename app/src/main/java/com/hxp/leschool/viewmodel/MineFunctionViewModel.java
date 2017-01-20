package com.hxp.leschool.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.leschool.adapter.MineFunctionAdapter;
import com.hxp.leschool.databinding.MinefunctionFmBinding;
import com.hxp.leschool.model.operate.MineFunctionModelOpt;
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

    public MineFunctionViewModel(MineFunctionFragment mineFunctionFragment, MinefunctionFmBinding minefunctionFmBinding) {

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
                Toast.makeText(mMineFunctionFragment.getActivity(), "点击了功能列表", Toast.LENGTH_SHORT).show();
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