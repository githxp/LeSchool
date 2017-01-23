package com.hxp.leschool.model.operate;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.MineFunctionModel;
import com.hxp.leschool.viewmodel.MineFunctionViewModel;

import java.util.ArrayList;

/**
 * Created by hxp on 17-1-15.
 */

public class MineFunctionModelOpt {

    public ArrayList<MineFunctionModel> mData = new ArrayList<>();
    private MineFunctionGetdataCallback mMineFunctionGetdataCallback;

    public MineFunctionModelOpt(MineFunctionViewModel mineFunctionViewModel) {
        mMineFunctionGetdataCallback = mineFunctionViewModel;
    }

    //获取数据
    public void getData() {
        mData.clear();
        mData.add(new MineFunctionModel("课件", R.mipmap.ic_launcher));
        mData.add(new MineFunctionModel("地图", R.mipmap.ic_launcher));
        Log.d("fragment", "数据获取回调发送方-MineFunction");
        mMineFunctionGetdataCallback.mineFuncionGetdataCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //获取数据回调
    public interface MineFunctionGetdataCallback {
        void mineFuncionGetdataCompleted();
    }
}
