package com.lzq.mvp.ui;

import android.view.View;
import android.widget.TextView;
import com.lzq.mvp.R;
import com.lzq.mvp.base.BaseActivity;
import com.lzq.mvp.presenter.Presenter_1;
import com.lzq.mvp.ui.view.View_1;

public class MainActivity extends BaseActivity<View_1,Presenter_1> implements View_1{

    private TextView mGetData,mShowData;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mGetData = (TextView) findViewById(R.id.tv_1);
        mShowData = (TextView) findViewById(R.id.tv_2);
    }

    @Override
    protected Presenter_1 createPresenter() {
        return new Presenter_1();
    }

    @Override
    protected View_1 createView() {
        return MainActivity.this;
    }

    @Override
    protected void initData() {
        mGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().bindData();
            }
        });
    }

    @Override
    public void setView(int size) {
        mShowData.setText("size="+size);
    }
}
