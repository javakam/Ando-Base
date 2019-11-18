package com.ando.base.core;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ando.base.utils.log.LogConfig;
import com.ando.base.utils.log.crash.CrashHandler;

import java.util.List;

/**
 * Title:Library
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/11/15 14:24
 */
public class Library {

    private Context context;                    //上下文
    private String rootDir;                     //根目录
    private String buildType;                   //构建类型
    private boolean isDebug;                    //测试类型
    private int databaseVersion;                //数据库版本

    private Library() {
    }

    public static Library getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        /**
         *
         */
        INSTANCE;
        private Library library;

        Singleton() {
            library = new Library();
        }

        public Library getInstance() {
            return library;
        }
    }

    /**
     * 初始化资源
     *
     * @param context 上下文
     * @param rootDir Sdcard根目录
     * @param isDebug 是否Debug模式
     */
    public static void init(Context context, @Nullable String rootDir, boolean isDebug) {
        Library instance = getInstance();
        instance.context = context;
        instance.rootDir = rootDir;
        instance.isDebug = isDebug;
        instance.buildType = isDebug ? "debug" : "release";
        LogConfig.initLogger(context, isDebug, isDebug);
        CrashHandler.initCrashHandler(context, isDebug);
    }

    /**
     * 初始化数据库
     *
     * @param databaseVersion 数据库版本
     * @param databaseBeans   映射字节码
     */
    public static void initDatabase(Context context, int databaseVersion, List<Class<?>> databaseBeans) {
//        Library instance = getInstance();
//        if (databaseBeans != null && databaseBeans.size() > 0) {
//            instance.databaseVersion = databaseVersion;
//            DataSource.get().init();
//        }
    }

    public Context getContext() {
        return context;
    }

    public String getRootDir() {
        return rootDir;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public String getBuildType() {
        return buildType;
    }

    public int getDatabaseVersion() {
        return databaseVersion;
    }

}
