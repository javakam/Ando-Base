package com.ando.base.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ando.base.utils.StatusBarUtil;
import com.ando.base.utils.StringUtils;
import com.ando.base.utils.ToastUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Title:BaseActivity
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/3/17 13:17
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseInterface, IBaseMvpInterface {

    /**
     * 系统DecorView的根View
     */
    protected View mView;

    /**
     * 是否退出App
     */
    private boolean isExit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initActivityStyle();
        initMvp();
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId > 0) {
            setContentView(layoutId);
            mView = findViewById(android.R.id.content);
        }

        initView(savedInstanceState);
        initListener();
        initData();
    }

    protected void initActivityStyle() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView = null;
    }

    /**
     * 获取String资源
     *
     * @param id
     * @return
     */
    protected List<String> getStringArray(@ArrayRes int id) {
        return Arrays.asList(getResources().getStringArray(id));
    }

    /**
     * 显示提示信息
     *
     * @param message
     */
    public void showMessage(String message) {
        if (mView != null) {
            Snackbar.make(mView, message, Snackbar.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 关闭输入法
     */
    public void hideKeyboard(View view) {
        if (view == null) {
            view = this.getCurrentFocus();
        }
        if (view != null) {
            ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 设置全屏
     */
    public void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置ToolBar和DrawerLayout
     */
    public void setupToolBar(int toolbarId, int toolBarTitleId, String title) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        //设置标题必须在setSupportActionBar之前才有效
        TextView toolBarTitle = (TextView) toolbar.findViewById(toolBarTitleId);
        toolBarTitle.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        toolBarTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 连续点击两次退出App
     */
    @SuppressLint("CheckResult")
    protected void exitBy2Click() {
        if (!isExit) {
            isExit = true; // 准备退出
            showMessage("再按一次退出");
            // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
            Flowable.timer(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                    .subscribe(aLong -> isExit = false);
        } else {
            finish();
        }
    }

    public void shortToast(String text) {
        ToastUtils.shortToast(StringUtils.noNull(text));
    }

    public void longToast(String text) {
        ToastUtils.longToast(StringUtils.noNull(text));
    }

}
