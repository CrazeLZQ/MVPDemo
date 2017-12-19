package com.lzq.mvp.base;

import com.lzq.mvp.ui.view.View_1;

/**
 * Created by LZQ on 2017/12/18 16:11.
 * Describe :
 */

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public T getView() {
        return view;
    }

    //绑定
    public void attachView(T view){
        this.view = view;
    }

    //分离
    public void detachView(){
        this.view = null;
    }
}
