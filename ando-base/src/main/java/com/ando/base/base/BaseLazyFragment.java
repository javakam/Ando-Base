package com.ando.base.base;

/**
 * Fragment 懒加载
 *
 * @author Changbao
 * @date 2019-11-18 20:55:14
 */
public abstract class BaseLazyFragment extends BaseFragment implements IBaseLazyInterface {

    @Override
    public void initData() {
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareFetchData();
    }

    @Override
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

    /*******************************todo 多种空视图处理方式待优化**********************************/

}