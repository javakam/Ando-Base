package com.ando.views.sample.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.ando.views.sample.R;
import com.ando.views.sample.utils.PermissionUtils;

/**
 * Title: SplashActivity
 * <p>
 * Description:
 * </p>
 * Author Changbao
 * Date 2018/11/7  15:16
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        setContentView(imageView);

        PermissionUtils.request(this);
    }
}
