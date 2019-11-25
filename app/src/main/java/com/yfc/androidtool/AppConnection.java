package com.yfc.androidtool;

import android.content.Context;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.yfc.androidu.HttpConnection;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class AppConnection {
    private Context context;
    private static AppConnection INSTANCE;
    private HttpConnection httpConnection;
    private static Retrofit retrofit;

    public static final String APP_URL = "https://h5.hoyard.cn:18081";

    private static void initInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AppConnection();
        }
        INSTANCE.initConnection(context);
    }

    /**
     * 获取配置后的Retrofit实例
     */
    public static Retrofit getClient(Context context) {
        initInstance(context);
        return retrofit;
    }

    private void initConnection(Context context) {
        this.context = context;
        if (httpConnection == null) httpConnection = new HttpConnection();
        //配置网络请求头
        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        /* 状态栏日志 */
        Interceptor chuckInterceptor = new ChuckInterceptor(context);
        /* Logcat */
        Interceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client;
        //测试实例
        client = httpConnection.getUnsafeClient(headerInterceptor, loggingInterceptor, chuckInterceptor);
        //线上实例
//        client = httpConnection.getUnsafeClient(headerInterceptor);

        retrofit = httpConnection.getRetrofit(client, APP_URL);
    }


    private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
            requestBuilder.addHeader("Client-Type", "Android");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }
}
