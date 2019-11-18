package com.ando.base.mvp;

public interface BaseContract {

    interface IView {

        /**
         * 请求错误
         */
        void showError(String msg);

        void showCompleted();

    }

    interface IPresenter<T> {
        /**
         * 绑定
         *
         * @param view view
         */
        void attachView(T view);

        /**
         * 解绑
         */
        void detachView();
    }
}