package com.ando.base.utils;

import android.app.Application;
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

    private static Application context;

    private AppUtils() {
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Application context) {
        AppUtils.context = context;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    public static void destroy() {
        AppUtils.context = null;
    }
}