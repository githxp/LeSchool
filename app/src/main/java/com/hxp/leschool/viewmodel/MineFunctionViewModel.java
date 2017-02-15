package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.adapter.MineFunctionAdapter;
import com.hxp.leschool.databinding.MinefunctionFmBinding;
import com.hxp.leschool.model.bean.MineFunctionModel;
import com.hxp.leschool.view.activity.DownloadActivity;
import com.hxp.leschool.view.activity.MapActivity;
import com.hxp.leschool.view.activity.RoundActivity;
import com.hxp.leschool.view.activity.ScoreActivity;
import com.hxp.leschool.view.fragment.MineFunctionFragment;
import com.hxp.leschool.widget.RecycleItemDivider;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-13.
 */


public class MineFunctionViewModel{

    public ArrayList<MineFunctionModel> mData = new ArrayList<>();
    private MineFunctionFragment mMineFunctionFragment;
    private MinefunctionFmBinding mMinefunctionFmBinding;
    private MineFunctionAdapter mMineFunctionAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public MineFunctionViewModel(final MineFunctionFragment mineFunctionFragment, MinefunctionFmBinding minefunctionFmBinding) {

        mMineFunctionFragment = mineFunctionFragment;
        mMinefunctionFmBinding = minefunctionFmBinding;

        mMineFunctionAdapter = new MineFunctionAdapter(this);

        mMinefunctionFmBinding.setMMineFunctionViewModel(this);

        get();
        Log.d("fragment", "用户功能opt获取数据");

        mLinearLayoutManager = new LinearLayoutManager(mMineFunctionFragment.getActivity(), LinearLayoutManager.VERTICAL, false);
        mMinefunctionFmBinding.rvMineFunction.setLayoutManager(mLinearLayoutManager);
        mMinefunctionFmBinding.rvMineFunction.setAdapter(mMineFunctionAdapter);
        mMinefunctionFmBinding.rvMineFunction.setItemAnimator(new DefaultItemAnimator());
        mMinefunctionFmBinding.rvMineFunction.addItemDecoration(new RecycleItemDivider(mMineFunctionFragment.getActivity(),RecycleItemDivider.VERTICAL_LIST));

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
                        break;
                    case 2:
                        mMineFunctionFragment.getActivity().startActivity(new Intent(mMineFunctionFragment.getActivity(), RoundActivity.class));
                        Toast.makeText(mMineFunctionFragment.getActivity(), "附近", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        mMineFunctionFragment.getActivity().startActivity(new Intent(mMineFunctionFragment.getActivity(), ScoreActivity.class));
                        Toast.makeText(mMineFunctionFragment.getActivity(), "成绩", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(mMineFunctionFragment.getActivity(), "设置", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //设置数据
    private void get(){
        mData.clear();
        mData.add(new MineFunctionModel("下载", R.drawable.ic_file));
        mData.add(new MineFunctionModel("地图", R.drawable.ic_map));
        mData.add(new MineFunctionModel("附近", R.drawable.ic_around));
        mData.add(new MineFunctionModel("成绩", R.drawable.ic_score));
        mData.add(new MineFunctionModel("设置", R.drawable.ic_settings));
    }
}
