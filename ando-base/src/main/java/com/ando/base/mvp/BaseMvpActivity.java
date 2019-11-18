package com.ando.base.mvp;

import com.ando.base.base.BaseActivity;
import com.ando.base.base.IBaseMvpInterface;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseMvpActivity<P extends BaseContract.IPresenter> extends BaseActivity
        implements BaseContract.IView, IBaseMvpInterface {

    protected P mPresenter;
    // private RemoteRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void initMvp() {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(BaseMvpActivity.this);
        }

        // this.mRepository = new RemoteRepository(IRequest.HOST);
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        unSubscribe();
    }

//    public RemoteRepository getRepository() {
//        return mRepository;
//    }

    public void addSubscriber(Disposable disposable) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    public void unSubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void showCompleted() {
    }

}