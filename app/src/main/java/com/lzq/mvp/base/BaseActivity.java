package com.lzq.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by LZQ on 2017/12/18 16:35.
 * Describe :
 */

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity{

    private P presenter;
    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1.设置布局
        setRootView();

        // 2.初始化界面
        initView();

        // 3.初始化mvp
        initMVP();

        // 4.初始化数据
        initData();
    }

    // 1.设置布局
    protected abstract void setRootView();

    // 2.初始化界面
    protected abstract void initView();

    // 3.抽象view和presenter
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

    // 4.初始化数据
    protected abstract void initData();

    /**
     * 启动一个Activity也可以添加一些启动动画
     */
    protected void startActivity(Class<? extends Activity> clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
        }
    }
}


