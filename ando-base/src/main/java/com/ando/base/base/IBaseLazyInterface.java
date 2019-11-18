package com.ando.base.base;

public interface IBaseLazyInterface extends IBaseInterface {

    /**
     * (!isDataInitiated || forceUpdate) -> 执行刷新数据的两个条件:1.未初始化数据;2.强制刷新
     *
     * @return
     */
    default boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /**
     * (!isDataInitiated || forceUpdate) -> 执行刷新数据的两个条件:1.未初始化数据;2.强制刷新
     *
     * @param forceUpdate
     * @return
     */
    default boolean prepareFetchData(boolean forceUpdate) {
        return false;
    }

    void initLazyData();
}
