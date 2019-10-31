package com.ando.base.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.StringSignature;

import java.util.UUID;


/**
 * Title: GlideUtils
 * <p>
 * Description:Glide图片加载
 * </p>
 * Author Changbao
 * Date 2018/10/19  14:46
 */
public class GlideUtils {

    public static void load(Activity activity, final ImageView imageView, String url,
                            @DrawableRes int placeholder, @DimenRes int size) {
        load(activity, imageView, url, placeholder, size, null);
    }

    /**
     * 加载图片
     *
     * @param activity
     * @param imageView
     * @param url         要加载的图片地址
     * @param placeholder 默认图片资源 int
     * @param size        大小
     */
    public static void load(final Activity activity, final ImageView imageView, String url,
                            @DrawableRes int placeholder, @DimenRes int size, SimpleTarget<GlideDrawable> target) {
        if (activity == null) {
            return;
        }
        if (!activity.isDestroyed()) {
//            url = checkUrl(url);
            DrawableRequestBuilder<String> builder
                    = Glide.with(activity).load(url) .crossFade()
                    .placeholder(placeholder).error(placeholder)
                    .signature(new StringSignature(UUID.randomUUID().toString()))
                    .transform(new CenterCrop(activity),
                            new GlideRoundTransform(activity, size))
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT);

            if (target == null) {
                target = new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        Log.e("123", "=======Bitmap resource========");
                        if (!activity.isDestroyed()) {
                            imageView.setImageDrawable(resource);
                        }
                    }
                };
            }
            builder.into(target);
        }
    }

    /**
     * 加载图片
     *
     * @param activity  上下文
     * @param imageView 控件
     * @param url       地址
     * @param resId     默认图片资源ID
     */
    public static void load(final Activity activity, final ImageView imageView, String url, int resId) {
        if (activity == null) {
            return;
        }
        //Fixed java.lang.IllegalArgumentException: You cannot start a load for a destroyed activity
        if (!activity.isDestroyed()) {
//            url = checkUrl(url);
            Glide.with(activity).load(url)
                    .crossFade()
                    .placeholder(resId).error(resId)
                    .signature(new StringSignature(UUID.randomUUID().toString()))
                    .skipMemoryCache(false) //传入参数为false时，则关闭内存缓存。
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)//关闭Glide的硬盘缓存机制
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(
                                GlideDrawable resource,
                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                            if (!activity.isDestroyed()) {
                                imageView.setImageDrawable(resource);
                            }
                        }
                    });
        } else {
            Log.e("123", "Glide load failed , activity is destroyed");
        }
    }

//    @Nullable
//    private static String checkUrl(String url) {
//        if (!TextUtils.isEmpty(url) && !url.startsWith("http") && !url.startsWith("https")) {
//            //Logger.w("before: " + url);
//            url = Net.getInstance().getImage() + url;
//        }
//        //Logger.w("after: " + url);
//        return url;
//    }
}
