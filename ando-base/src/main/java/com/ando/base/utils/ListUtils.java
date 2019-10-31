package com.ando.base.utils;

import java.util.List;

/**
 * Title:ListUtils
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/9 16:30
 */
public class ListUtils {

    public static boolean isEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static int getSize(List list) {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
