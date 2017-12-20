package com.lzq.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LZQ on 2017/12/20 09:55.
 * Describe :
 */

public abstract class BaseFragment<V extends BaseView,P extends BasePresenter<V>> extends Fragment {

    public Activity mActivity;

    private P presenter;
    private V view;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews();
        return view;
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化mvp
        initMVP();
        //初始化数据
        initData();
    }

    //1.初始化View
    public abstract View initViews();

    // 2.抽象view和presenter
    public void initMVP(){
        presenter = createPresenter();
        if (presenter == null)
            throw new NullPointerException("presenter is null");
        view = createView();
        if (view == null)
            throw new NullPointerException("view is null");
        presenter.attachView(view);
    }

    protected abstract P createPresenter();
    protected abstract V createView();

    public P getPresenter() {
        return presenter;
    }

    //3.初始化数据
    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
        }
    }
}
