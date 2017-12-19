package com.lzq.mvp.http;

import com.lzq.mvp.http.config.HttpConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LZQ on 2017/11/27 15:32.
 * Describe : 封装的retrofit
 */

public class RetrofitFactory {

    private static OkHttpClient httpClient = new OkHttpClient
            .Builder()
            .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)//设置连接超时时间
            .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)//设置写入超时时间
            .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
            .build();

    private static APIFunction retrofitService = new Retrofit.Builder()
            .baseUrl(HttpConfig.BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create())
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(APIFunction.class);

    public static APIFunction getInstance() {
        return retrofitService;
    }
}
