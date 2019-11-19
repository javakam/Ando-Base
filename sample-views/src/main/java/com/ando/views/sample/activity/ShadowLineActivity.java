package com.ando.views.sample.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.ando.views.sample.R;


/**
 * Title: ShadowLineActivity
 * <p>
 * Description: <p>
 * https://my.oschina.net/deepSprings/blog/1808945  <p>
 * https://blog.csdn.net/qq_22078107/article/details/53453447
 * </p>
 *
 * @author Changbao
 * @date 2018/11/19  16:32
 */
public class ShadowLineActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_line);
        View v = findViewById(R.id.view_shadow_line);
        v.setBackgroundResource(R.drawable.rectangle_shadow_line_bottom);
    }
}