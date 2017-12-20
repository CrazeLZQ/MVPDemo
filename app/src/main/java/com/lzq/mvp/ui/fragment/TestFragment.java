package com.lzq.mvp.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.lzq.mvp.R;
import com.lzq.mvp.base.BaseFragment;
import com.lzq.mvp.presenter.Presenter_1;
import com.lzq.mvp.ui.view.View_1;

/**
 * Created by LZQ on 2017/12/20 10:09.
 * Describe :
 */

public class TestFragment extends BaseFragment<View_1,Presenter_1> implements View_1{

    private TextView mGetData,mShowData;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_text,null);
        mGetData = (TextView) view.findViewById(R.id.tv_3);
        mShowData = (TextView) view.findViewById(R.id.tv_4);
        return view;
    }

    @Override
    protected Presenter_1 createPresenter() {
        return new Presenter_1();
    }

    @Override
    protected View_1 createView() {
        return this;
    }

    @Override
    public void initData() {
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
