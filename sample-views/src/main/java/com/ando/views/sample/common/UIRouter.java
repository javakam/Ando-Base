package com.ando.views.sample.common;

import com.ando.views.sample.R;
import com.ando.views.sample.activity.BannerActivity;
import com.ando.views.sample.activity.CalendarActivity;
import com.ando.views.sample.activity.CalendarMonthActivity;
import com.ando.views.sample.activity.FloatButtonActivity;
import com.ando.views.sample.activity.HorizonRecycleViewActivity;
import com.ando.views.sample.activity.ProgressBarActivity;
import com.ando.views.sample.activity.ShadowLineActivity;
import com.ando.views.sample.activity.SpinnerActivity;
import com.ando.views.sample.addresspicker.AddressPickerActivity;
import com.ando.views.sample.basic.BasicViewActivity;
import com.ando.views.sample.basic.BasicViewCenterActivity;
import com.ando.views.sample.basic.animation.BasicViewAnimActivity;

/**
 * Title: UIRouter
 * <p>
 * Description:
 * </p>
 * Author Changbao
 * Date 2018/10/22  13:27
 */
public enum UIRouter {
    /**
     *
     */
    PROGRESSBAR1(0, "进度圆环", R.drawable.ic_launcher_background, ProgressBarActivity.class),
    DATE_SELECT(1, "日期选择", R.drawable.ic_launcher_background, HorizonRecycleViewActivity.class),
    Calendar1(2, "日历", R.drawable.ic_launcher_background, CalendarActivity.class),
    Calendar3(3, "日历(RV)", R.drawable.ic_launcher_background, CalendarMonthActivity.class),
    ADDRESS_SELECT(4, "地址选择", R.drawable.ic_launcher_background, AddressPickerActivity.class),
    SHADOW_LINE(5, "阴影效果", R.drawable.ic_launcher_background, ShadowLineActivity.class),
    BASIC_VIEW(6, "绘制基础1-3", R.drawable.ic_launcher_background, BasicViewActivity.class),
    BASIC_VIEW_CENTER(7, "绘制基础4", R.drawable.ic_launcher_background, BasicViewCenterActivity.class),
    BASIC_VIEW_ANIM(8, "绘制基础-属性动画", R.drawable.ic_launcher_background, BasicViewAnimActivity.class),
    // STRING_BUILDER(8, "多层SpannableStringBuilder", R.drawable.ic_launcher_background, StringBuilderActivity.class),
    SPINNER(9, "下拉框", R.drawable.ic_launcher_background, SpinnerActivity.class),
    FLOAT_BUTTON(10, "全局悬浮窗", R.drawable.ic_launcher_background, FloatButtonActivity.class),
    BANNER(11, "轮播图", R.drawable.ic_launcher_background, BannerActivity.class);

    public static Class<?> findClassById(int id) {
        for (UIRouter r : UIRouter.values()) {
            if (r.getId() == id) {
                return r.clz;
            }
        }
        return null;
    }

    private int id;
    private String description;
    private int icon;
    private Class<?> clz;

    UIRouter(int id, String name, int icon, Class<?> clz) {
        this.id = id;
        this.description = name;
        this.icon = icon;
        this.clz = clz;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getIcon() {
        return icon;
    }

    public Class<?> getClz() {
        return clz;
    }
}
