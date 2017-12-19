package com.lzq.mvp.http;

import com.lzq.mvp.http.bean.BaseEntity;
import com.lzq.mvp.http.bean.Test;
import com.lzq.mvp.http.config.URLContant;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by LZQ on 2017/11/27 16:02.
 * Describe : 接口
 */

public interface APIFunction {

    @POST(URLContant.login)
    Observable<BaseEntity<Test>> login(@Query("psw") String psw, @Query("loginName") String loginName);

    @POST(URLContant.login_token_url)
    Call<String> loginByToken(@Query("mobile") String mobile, @Query("token") String cookie);

    /*@GET(URLContant.test)
    Observable<BaseEntity<Test>> test();*/

    @GET("http://mj.znjoy.com/gamenews/nav")
    Observable<Test.RSP> test();

    //上传单张图片
    @POST("")
    Observable<Object> imageUpload(@Part() MultipartBody.Part img);

    //上传多张图片
    @POST("")
    Observable<Object> imagesUpload(@Part() List<MultipartBody.Part> imgs);
}
