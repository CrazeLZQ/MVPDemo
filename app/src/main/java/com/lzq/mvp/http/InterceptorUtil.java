package com.lzq.mvp.http;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;

/**
 * Created by LZQ on 2017/11/28 14:23.
 * Describe : 拦截器工具类!
 */

public class InterceptorUtil {

    public static String TAG = "----";
    private static String Token = "";
    public final static Charset UTF8 = Charset.forName("UTF-8");

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC);//设置打印数据的级别
    }

    /**
     * token验证的拦截器
     *
     * @return
     */
    public static Interceptor tokenInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拿到请求体,并添加header携带上token
                Request mRequest = chain.request().newBuilder()
                        .addHeader("Token", Token)
                        .build();
                //拿到响应体
                Response mResponse = chain.proceed(mRequest);

                if (mResponse.code() == 201) {
                    //重新获取新token
                    //这用了一个特殊接口来获取新的Token
                    Call<String> call = RetrofitFactory.getInstance().loginByToken("123456", Token);
                    //拿到请求体
                    Request tokenRequest = call.request();
                    //获取响应体
                    Response tokenResponse = chain.proceed(tokenRequest);
                    //我这假设新的token是在header里返回的
                    //我们拿到新的token头
                    List<String> listHeader = tokenResponse.headers().values("Token");
                    if (listHeader != null) {
                        //重新赋值到新的token
                        Token = listHeader.get(0);
                    }

                    //这是只需要替换掉之前的token重新请求接口就行了
                    Request newRequest = mRequest.newBuilder()
                            .header("Token", Token)
                            .build();
                    return chain.proceed(newRequest);
                }
                return mResponse;
            }
        };
    }
}
