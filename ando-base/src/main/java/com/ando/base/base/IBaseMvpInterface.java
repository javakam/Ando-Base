package com.ando.base.base;

public interface IBaseMvpInterface extends IBaseInterface {

    default void initMvp() {
    }

    void initPresenter();
}
