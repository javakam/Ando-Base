package com.ando.base.utils;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Title:ClickUtils
 * <p>
 * Description:按键防抖动
 * </p>
 *
 * @author Changbao
 * Date 2017/10/29 17:14
 */
public class ClickUtils {

    public static void noShake(View view, final OnClickListener listener) {
        RxView.clicks(view)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        listener.onClick();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface OnClickListener {
        void onClick();
    }
}
