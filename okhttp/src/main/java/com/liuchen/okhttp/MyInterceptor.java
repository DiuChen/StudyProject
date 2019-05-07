package com.liuchen.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: 刘晨
 * Date: 2019/4/18 15:42
 */
public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .header("RANGE", "bytes=" + 3165134 + "-")
                .build();
        return chain.proceed(request);
    }
}
