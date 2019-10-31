package com.ando.base.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * TODO 自定义 ResourceSubscriber
 */
public abstract class RxSubscriber<T> extends ResourceSubscriber<T> {

    public RxSubscriber() {
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable throwable) {

        //网络异常
        if (throwable instanceof ConnectException || throwable instanceof SocketTimeoutException || throwable instanceof TimeoutException) {
            _onNetWorkConnectError(throwable.getMessage());
        } else if (throwable instanceof UnknownHostException) {
            _onCommonError(throwable.getMessage());
        } else {
            _onCommonError(throwable.getMessage());
        }
    }

    @Override
    public void onComplete() {
        _onComplete();
    }

    public abstract void _onNext(T t);//onNext()

    public abstract void _onNetWorkConnectError(String message);

    //其他错误（非网络）
    public abstract void _onCommonError(String message);

    public abstract void _onComplete();//onComplete()

}