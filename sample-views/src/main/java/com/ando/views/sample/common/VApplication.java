package com.ando.views.sample.common;

import android.app.Application;

import com.ando.views.floatwindow.FloatWindow;
import com.ando.views.sample.activity.FloatButtonActivity;
import com.ando.views.sample.addresspicker.bean.AddressBean;
import com.ando.views.sample.addresspicker.dao.DaoUtils;
import com.ando.views.sample.basic.animation.BasicViewAnimActivity;
import com.ando.views.sample.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: VApplication
 * <p>
 * Description:
 * </p>
 * Author Changbao
 * Date 2018/10/22  14:01
 */
public class VApplication extends Application {

    private static final String TAG = "VApplication";

    private static final VApplication instance = new VApplication();

    public static VApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //1
        AppUtils.getInstance().initialize(this, "ViewGeneral");// BuildConfig.ROOT_DIR
        //2
        List<Class<?>> list = new ArrayList<>();
        list.add(AddressBean.class);
        DaoUtils.initDatabase(this, 1, list);
//        Library.init(this, BuildConfig.ROOT_DIR , true);//+ "/" + BuildConfig.ROOT_PATH
//        IDictionary.initDictionary(this);

        //3 悬浮窗
        List<Class<?>> activities = new ArrayList<>();
        activities.add(MainActivity.class);
        activities.add(FloatButtonActivity.class);
        activities.add(BasicViewAnimActivity.class);
        FloatWindow.create(this, activities);
    }
}