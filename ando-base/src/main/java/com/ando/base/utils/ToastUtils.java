package com.ando.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Title:ToastUtils
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/1/19
 */
@SuppressLint("ShowToast")
public class ToastUtils {

    private static Toast toast;

    public static void shortToast(String text) {
        shortToast(AppUtils.getContext(), text);
    }

    public static void shortToast(Context context, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void longToast(String text) {
        longToast(AppUtils.getContext(), text);
    }

    public static void longToast(Context context, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

}