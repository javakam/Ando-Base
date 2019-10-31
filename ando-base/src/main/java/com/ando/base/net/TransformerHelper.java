package com.ando.base.net;

import android.text.TextUtils;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TransformerHelper {

    private final static FlowableTransformer TRANSFORMER = upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    /**
     * io线程-主线程
     */
    public static <T> FlowableTransformer<T, T> io2main() {
        return (FlowableTransformer<T, T>) TRANSFORMER;
    }

    /**
     * 将服务器返回的Response<T>流装换成T流
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleResponse(Class<?> clz) {
        return flowable -> flowable.flatMap((Function<BaseResponse<T>, Publisher<T>>) t -> {
            if (t == null || (t instanceof List && ((List) t).size() == 0)) {
                return Flowable.error(new Exception("返回的数据实体为空"));//todo 自定义异常
            } else {
                if (t.result != null) {
                    if ((t.result instanceof String) && TextUtils.isEmpty((String) t.result)) {
                        return Flowable.error(new Throwable(t.status));
                    }
                    return Flowable.just(t.result);
                }
                return Flowable.error(new Throwable("未获取到有效数据"));
            }
        });
    }
}