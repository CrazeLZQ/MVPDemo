package com.lzq.mvp.http.base;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.lzq.mvp.http.widget.ProgressDialog;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by LZQ on 2017/11/28 09:24.
 * Describe :
 */

public abstract class BaseObserver<T> implements Observer<T> {

    protected Context mContext;

    public BaseObserver(){}

    public BaseObserver(Context ctx){
        this.mContext = ctx;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        //开始调用请求
        onRequestStart();
    }

    @Override
    public void onNext(@NonNull T t) {
        onRequestEnd();
        try {
            onSuccees(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* if (baseEntity.isSuccess()) {
            try {
                onSuccees(baseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(baseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(T t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(T t) throws Exception {
        onSuccees(t);
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
        showProgressDialog();
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
        ProgressDialog.show(mContext, false, "请稍后");
    }

    public void closeProgressDialog() {
        ProgressDialog.cancle();
    }
}
