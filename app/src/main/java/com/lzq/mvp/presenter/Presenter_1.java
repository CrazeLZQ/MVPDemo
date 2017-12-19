package com.lzq.mvp.presenter;

import com.lzq.mvp.base.BasePresenter;
import com.lzq.mvp.http.base.BaseObserver;
import com.lzq.mvp.http.bean.Test;
import com.lzq.mvp.model.Model_1;
import com.lzq.mvp.ui.view.View_1;

/**
 * Created by LZQ on 2017/12/18 15:32.
 * Describe :
 */

public class Presenter_1 extends BasePresenter<View_1>{

    private Model_1 model_1;

    public Presenter_1(){
        model_1 = new Model_1();
    }

    //进行数据绑定
    public void bindData(){
        model_1.getData(new BaseObserver<Test.RSP>() {
            @Override
            protected void onSuccees(Test.RSP rsp) throws Exception {
                if (getView() != null){
                    getView().setView(rsp.newsNodes.size());
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }
}

