package com.ando.base.utils;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Title:AppUtils
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/3/17 14:45
 */
public final class AppUtils {

    private static AppUtils instance;
    private Context context;

    private AppUtils() {
    }

    public static synchronized AppUtils getInstance() {
        if (instance == null) {
            instance = new AppUtils();
        }
        return instance;
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public void init(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }
}