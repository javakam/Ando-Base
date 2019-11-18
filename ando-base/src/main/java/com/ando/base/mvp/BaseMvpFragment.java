package com.ando.base.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ando.base.base.BaseFragment;
import com.ando.base.base.IBaseMvpInterface;

public abstract class BaseMvpFragment<P extends BaseContract.IPresenter> extends BaseFragment
        implements BaseContract.IView, IBaseMvpInterface {

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initMvp();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initMvp() {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void showCompleted() {
    }

}