package com.ando.base;


import androidx.multidex.MultiDexApplication;

import com.ando.base.utils.AppUtils;

/**
 * Title: BaseApplication
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/8/6  9:13
 */
public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.getInstance().init(this);
    }
}
