package com.ando.base.mvp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseMvpRefreshFragment<T extends BaseContract.IPresenter, K>
        extends BaseMvpFragment<T> implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRecycler;
    protected SwipeRefreshLayout mRefresh;
    protected boolean mIsRefreshing;
    protected List<K> mList = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        mRefresh = rootView.findViewById(R.id.refresh);
//        mRecycler = rootView.findViewById(R.id.recycler);

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_dark);
        mRefresh.setOnRefreshListener(this);

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        initRecyclerView();
    }

    protected abstract void initRecyclerView();

    @Override
    public void onRefresh() {
        mIsRefreshing = true;
        onRefreshData();
        mRefresh.setRefreshing(false);
    }

    protected abstract void onRefreshData();

    protected void onRefreshComplete() {
        if (mIsRefreshing) {
            mList.clear();
            clearCachedData();
            Toast.makeText(activity, "刷新成功", Toast.LENGTH_SHORT).show();
        }
        mIsRefreshing = false;
    }

    protected abstract void clearCachedData();
}