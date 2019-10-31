package com.ando.base.mvp;

import android.content.Context;

import androidx.annotation.ArrayRes;
import androidx.annotation.StringRes;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T extends BaseContract.IView> implements BaseContract.IPresenter<T> {

    protected Context mContext;
    private Reference<T> mViewRef;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(T view) {
        this.mViewRef = new WeakReference<>(view);
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void detachView() {
        unSubscribe();
        mContext = null;
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public void unSubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscriber(Disposable disposable) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    /**
     * 获取View
     */
    public T getView() {
        if (isAttached()) {
            return mViewRef.get();
        }
        return null;
    }

    /**
     * 判断View是否还与该Presenter绑定
     */
    public boolean isAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 获取String资源
     */
    protected String getString(@StringRes int res) {
        return mContext.getString(res);
    }

    public List<String> getStringArray(@ArrayRes int id) {
        return Arrays.asList(mContext.getResources().getStringArray(id));
    }
}