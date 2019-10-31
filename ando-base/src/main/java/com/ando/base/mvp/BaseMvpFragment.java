package com.ando.base.mvp;


import com.ando.base.base.BaseFragment;

public abstract class BaseMvpFragment<P extends BaseContract.IPresenter> extends BaseFragment implements BaseContract.IView {

    protected P mPresenter;

    @Override
    protected void initMvp() {
        super.initMvp();
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract void initPresenter();

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
    public void complete() {

    }

}