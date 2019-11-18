package com.ando.base.utils.log;

import android.content.Context;

import com.ando.base.core.Config;
import com.ando.base.utils.FileUtils;

/**
 * Title:LogConfig
 * <p>
 * Description:日志配置
 * </p>
 *
 * @author Changbao
 * Date 2017/8/29 12:01
 */
public class LogConfig {

    static final String LOG_REPORTER_EXTENSION = ".log";
    private static LogConfig config;

    private boolean debug;              // 开启控制台输出模式
    private boolean logFile;            // 开启客户端本地日志记录模式
    private String logFilePath;         // 本地日志记录的路径

    private LogConfig() {

    }

    static synchronized LogConfig getInstance() {
        if (config == null) {
            config = new LogConfig();
        }
        return config;
    }

    /**
     * @param debug   开启控制台输出模式
     * @param logFile 开启客户端本地日志记录模式
     */
    public static void initLogger(Context context, boolean debug, boolean logFile) {
        initLogger(debug, logFile, FileUtils.getCachePath(context, Config.INFO));
    }

    private static void initLogger(boolean debug, boolean logFile, String logFilePath) {
        ThreadPool.initThreadPool(3);// 初始化线程池
        LogConfig instance = LogConfig.getInstance();
        instance.debug = debug;
        instance.logFile = logFile;
        instance.logFilePath = logFilePath;
    }

    boolean isDebug() {
        return debug;
    }

    boolean isLogFile() {
        return logFile;
    }

    String getLogFilePath() {
        return logFilePath;
    }

}
