package com.ando.base.mvp;

import androidx.fragment.app.FragmentTransaction;

import com.ando.base.base.IBaseLazyInterface;
import com.ando.base.mvp.BaseContract;
import com.ando.base.mvp.BaseMvpFragment;

/**
 * Fragment 懒加载 + MVP
 * <pre>
 *     占用了 initData() 方法
 * </pre>
 *
 * @author Changbao
 * @date 2019年03月08日
 */
public abstract class BaseMvpLazyFragment<P extends BaseContract.IPresenter> extends BaseMvpFragment<P>
        implements IBaseLazyInterface {

    /**
     * After
     * <pre>
     *     @Override
     *     public void onActivityCreated(Bundle savedInstanceState) {
     *         super.onActivityCreated(savedInstanceState);
     *          initData() ;
     *     }
     * </pre>
     * {@link com.ando.base.base.BaseFragment#onActivityCreated}
     */
    @Override
    public void initData() {
        prepareFetchData();
    }

    /**
     * @deprecated Use {@link FragmentTransaction#setMaxLifecycle}
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareFetchData();
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        //(!isDataInitiated || forceUpdate) -> 执行刷新数据的两个条件:1.未初始化数据;2.强制刷新
        if (isActivityCreated && isVisibleToUser && (!isDataInitiated || forceUpdate)) {
            /*
             * 在该方法中初始化 Fragment 的数据
             * <p>
             * initLayout -> initViews -> onActivityCreated -> initData -> initLazyData
             */
            initLazyData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    /*******************************todo 多种空视图处理方式待优化**********************************/

}