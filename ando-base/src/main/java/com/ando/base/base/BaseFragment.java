package com.ando.base.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ando.base.utils.StringUtils;
import com.ando.base.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

/**
 * Title:BaseFragment
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/3/17 13:24
 */
public abstract class BaseFragment extends Fragment implements IBaseInterface {

    protected BaseActivity activity;
    protected View rootView;
    //
    protected boolean isActivityCreated;    //Activity是否已创建
    protected boolean isVisibleToUser;      //Fragment是否对用户可见
    protected boolean isDataInitiated;      //Fragment是否已加载过数据

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initView(savedInstanceState);
        initListener();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        initData();
    }

    /**
     * @deprecated Use {@link FragmentTransaction#setMaxLifecycle}
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        rootView = null;
        activity = null;
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        if (getActivity() != null) {
            Intent intent = new Intent();
            intent.setClass(activity, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    public void shortToast(String text) {
        ToastUtils.shortToast(StringUtils.noNull(text));
    }

    public void longToast(String text) {
        ToastUtils.longToast(StringUtils.noNull(text));
    }

}