package com.ando.views.sample.basic;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ando.views.sample.R;
import com.ando.views.sample.activity.BaseActivity;

/**
 * Title:BasicViewActivity
 * <p>
 * Description:绘制基础
 * </p>
 * @author Changbao
 * @date 2018/12/11 10:57
 */
public class BasicViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view);
    }
}
