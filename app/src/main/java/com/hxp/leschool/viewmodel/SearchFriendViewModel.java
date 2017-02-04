package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.adapter.SearchFriendAdapter;
import com.hxp.leschool.databinding.SearchfriendAtBinding;
import com.hxp.leschool.model.bean.SearchFriendModel;
import com.hxp.leschool.model.operate.SearchFriendModelOpt;
import com.hxp.leschool.model.operate.SearchFriendModelOpt.SearchFriendallback;
import com.hxp.leschool.view.activity.SearchFriendActivity;
import com.hxp.leschool.view.activity.SendAddReqActivity;


/**
 * Created by hxp on 17-1-22.
 */


public class SearchFriendViewModel implements SearchFriendallback {

    public SearchFriendModelOpt mSearchFriendModelOpt;
    private SearchFriendActivity mSearchFriendActivity;
    private SearchfriendAtBinding mSearchfriendAtBinding;
    private SearchFriendAdapter mSearchFriendAdapter;

    public SearchFriendViewModel(SearchFriendActivity searchFriendActivity, SearchfriendAtBinding searchfriendAtBinding) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mSearchFriendActivity = searchFriendActivity;
        mSearchfriendAtBinding = searchfriendAtBinding;

        mSearchFriendModelOpt = new SearchFriendModelOpt(this);
        mSearchFriendAdapter = new SearchFriendAdapter(this);

        mSearchfriendAtBinding.setMSearchFriendViewModel(this);

        mSearchfriendAtBinding.rvSearchFriendContent.setLayoutManager(new LinearLayoutManager(mSearchFriendActivity, LinearLayoutManager.VERTICAL, false));
        mSearchfriendAtBinding.rvSearchFriendContent.setAdapter(mSearchFriendAdapter);

        mSearchfriendAtBinding.swifreshSearchFriendContent.setProgressViewOffset(true, 0, 50);
        mSearchfriendAtBinding.swifreshSearchFriendContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSearchfriendAtBinding.swifreshSearchFriendContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        String userName = mSearchfriendAtBinding.etSearchFriendUserName.getText().toString();
                        if (!userName.equals("")) {
                            mSearchFriendModelOpt.refresh(userName);
                        } else {
                            mSearchfriendAtBinding.swifreshSearchFriendContent.setRefreshing(false);
                            Toast.makeText(mSearchFriendActivity, "请输入用户名", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        mSearchFriendAdapter.setOnItemClickListener(new SearchFriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SearchFriendModel searchFriendModel = mSearchFriendModelOpt.mData.get(position);
                Intent intent = new Intent(mSearchFriendActivity, SendAddReqActivity.class);
                intent.putExtra("userName", searchFriendModel.getUserName());
                mSearchFriendActivity.startActivity(intent);
            }
        });
    }

    public void btn_SearchFriend_search(View view) {
        String userName = mSearchfriendAtBinding.etSearchFriendUserName.getText().toString();
        if (!userName.equals("")) {
            mSearchFriendModelOpt.refresh(userName);
            Log.d("fragment", "搜索用户名：" + userName);
        } else {
            Toast.makeText(mSearchFriendActivity, "请输入用户名", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void refresh() {
        Log.d("fragment", "SearchFriendModelOpt刷新数据回调成功接收方");
        mSearchfriendAtBinding.swifreshSearchFriendContent.setRefreshing(false);
        mSearchFriendAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshErr() {
        Log.d("fragment", "SearchFriendModelOpt刷新数据回调失败接收方");
    }
}
