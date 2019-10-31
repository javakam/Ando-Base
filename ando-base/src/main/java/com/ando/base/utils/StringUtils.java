package com.ando.base.utils;

import android.text.TextUtils;

import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author machangbao
 * @date 2019-05-17 16:56:26
 */
public class StringUtils {
    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String noNull(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }
}
