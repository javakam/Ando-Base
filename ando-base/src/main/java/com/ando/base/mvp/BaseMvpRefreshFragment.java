package com.ando.base.mvp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpRefreshFragment<T extends BaseContract.IPresenter, Data>
        extends BaseMvpFragment<T> implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRecycler;
    protected SwipeRefreshLayout mRefresh;
    protected boolean isRefreshing;
    protected List<Data> mList = new ArrayList<>();


    @Override
    public void initView(Bundle savedInstanceState) {
//        mRefresh = rootView.findViewById(R.id.refresh);
//        mRecycler = rootView.findViewById(R.id.recycler);

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_dark);
        mRefresh.setOnRefreshListener(this);

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        initViews(savedInstanceState);
    }

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void onRefreshData();

    protected abstract void clearCachedData();

    @Override
    public void onRefresh() {
        isRefreshing = true;
        onRefreshData();
        mRefresh.setRefreshing(false);
    }

    protected void onRefreshCompleted() {
        if (isRefreshing) {
            mList.clear();
            clearCachedData();
            //Toast.makeText(activity, "刷新成功", Toast.LENGTH_SHORT).show();
        }
        isRefreshing = false;
    }

}