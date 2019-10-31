package com.ando.base.base;

import android.os.Bundle;

/**
 * Fragment 懒加载
 *
 * @author machangbao
 * @date 2019年03月08日
 */
public abstract class BaseLazyFragment extends BaseFragment {

    protected boolean isActivityCreated;    //Activity是否已创建
    protected boolean isVisibleToUser;      //Fragment是否对用户可见
    protected boolean isDataInitiated;      //Fragment是否已加载过数据

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isActivityCreated && isVisibleToUser && (!isDataInitiated || forceUpdate)) {
            /**
             * 在该方法中初始化 Fragment 的数据
             * <p>
             * initLayout -> initViews -> initData
             */
            initLazyData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    protected abstract void initLazyData();

    /*******************************todo 多种空视图处理方式待优化**********************************/
}
