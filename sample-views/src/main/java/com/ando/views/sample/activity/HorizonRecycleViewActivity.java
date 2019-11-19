package com.ando.views.sample.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ando.views.recycler_hor_date.GalleryAdapter;
import com.ando.views.sample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: HorizonRecycleViewActivity
 * <p>
 * Description:横向RecyclerView
 * </p>
 * Author Changbao
 * Date 2018/10/17  9:25
 */
public class HorizonRecycleViewActivity extends BaseActivity {
    private RecyclerView mRvhorizontal;
    private GalleryAdapter mAdapter;
    private List<Integer> mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("日期选择(横向)");
        setContentView(R.layout.activity_rv_horizon);
        mData = new ArrayList<>(Arrays.asList(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mRvhorizontal = findViewById(R.id.rv_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvhorizontal.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(this, mData);
        mRvhorizontal.setAdapter(mAdapter);

    }
}
