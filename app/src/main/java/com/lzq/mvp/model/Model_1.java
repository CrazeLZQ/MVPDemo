package com.lzq.mvp.model;

import com.lzq.mvp.base.BaseModel;
import com.lzq.mvp.http.RetrofitFactory;
import com.lzq.mvp.http.base.BaseObserver;
import com.lzq.mvp.http.bean.Test;

/**
 * Created by LZQ on 2017/12/18 15:21.
 * Describe :
 */

public class Model_1 extends BaseModel{

    public void getData(BaseObserver<Test.RSP> baseObserver){
        RetrofitFactory.getInstance().test().compose(this.<Test.RSP>setThread())
                .subscribe(baseObserver);
    }
}
