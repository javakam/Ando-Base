package com.ando.base.base;

import android.os.Bundle;

public interface IBaseInterface {

    void initView(Bundle savedInstanceState);

    default void initListener() {
    }

    default void initData() {
    }

    int getLayoutId();
}
